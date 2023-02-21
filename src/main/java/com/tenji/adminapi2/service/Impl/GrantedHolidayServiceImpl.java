package com.tenji.adminapi2.service.Impl;

import com.tenji.adminapi2.api.ApiResponseCode;
import com.tenji.adminapi2.dto.GrantedHolidayForm;
import com.tenji.adminapi2.dto.UserInfo;
import com.tenji.adminapi2.dto.UserInfoHolder;
import com.tenji.adminapi2.dto.config.MasterClassCode;
import com.tenji.adminapi2.exception.ApiException;
import com.tenji.adminapi2.exception.BizException;
import com.tenji.adminapi2.mapper.EmployeeMapper;
import com.tenji.adminapi2.mapper.GrantedHolidayLogMapper;
import com.tenji.adminapi2.mapper.GrantedHolidayMapper;
import com.tenji.adminapi2.model.*;
import com.tenji.adminapi2.mapper.MasterClassMapper;
import com.tenji.adminapi2.model.GrantedHoliday;
import com.tenji.adminapi2.service.GrantedHolidayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class GrantedHolidayServiceImpl implements GrantedHolidayService {

    @Autowired
    GrantedHolidayMapper grantedHolidayMapper;

    @Autowired
    private   EmployeeMapper employeeMapper;

    @Autowired
    private MasterClassMapper masterClassMapper;

    @Autowired
    private GrantedHolidayLogMapper grantedHolidayLogMapper;

    @Override
    public List<GrantedHoliday> getByEmployeeId(long employeeId) {
        return grantedHolidayMapper.selectByEmployeeId(employeeId);
    }

    @Override
    public GrantedHoliday getById(long id) {
        return grantedHolidayMapper.selectByPrimaryKey(id);
    }

    @Override
    public int add(GrantedHolidayForm grantedHolidayForm){

        Employee employee =employeeMapper.selectByPrimaryKey(grantedHolidayForm.getEmployeeId());
        if(Objects.isNull(employee)){
            throw new BizException("社員データはありません。");
        }
        int returnNum=0;
        try{
            GrantedHoliday grantedHoliday = new GrantedHoliday();

            UserInfo userInfo= UserInfoHolder.get();
            grantedHoliday.setUserId(userInfo.getUserId());

            grantedHoliday.setEmployeeId(grantedHolidayForm.getEmployeeId());
            Date grantedDate=grantedHolidayForm.getGrantedDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar    calendar =Calendar.getInstance();
            calendar.setTime(grantedDate);
            calendar.add(Calendar.YEAR,1);
            Date expiryDate =calendar.getTime();
            calendar.add(Calendar.YEAR,1);
            Date carryOverExpiryDate =calendar.getTime();

            grantedHoliday.setGrantedDate(grantedHolidayForm.getGrantedDate());

            grantedHoliday.setExpiryDate(expiryDate);
            grantedHoliday.setCarryoverExpiryDate(carryOverExpiryDate);

            Date nowDate = new Date();
            Date employeeDate = employee.getEmploydate();
            Calendar bef = Calendar.getInstance();
            Calendar aft = Calendar.getInstance();

            bef.setTime(simpleDateFormat.parse(simpleDateFormat.format(employeeDate)));
            aft.setTime(simpleDateFormat.parse(simpleDateFormat.format(nowDate)));
            float year = aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR);

            grantedHoliday.setGrantedServiceYears(year);

            MasterClass masterClass= masterClassMapper.getByTypeAndCode(MasterClassCode.MASTER_TYPE1.getCode(),MasterClassCode.GHST02.getCode());
            if(Objects.isNull(masterClass)){
                throw new BizException("master code データはありません。");
            }
            grantedHoliday.setStatusCode(masterClass.getCode());

            grantedHoliday.setGrantedDays(grantedHolidayForm.getGrantedDays());

            grantedHoliday.setUsedDays(0);
            grantedHoliday.setUnusedDays(grantedHolidayForm.getGrantedDays());
            grantedHoliday.setRemainingDays(grantedHolidayForm.getGrantedDays());

            grantedHoliday.setCreateTime(nowDate);
            grantedHoliday.setUpdateTime(nowDate);

            returnNum=  grantedHolidayMapper.insert(grantedHoliday);
            if(returnNum >0){
             int days=  grantedHolidayMapper.countActiveDays(grantedHolidayForm.getEmployeeId());
                employee.setRemainingDays(days);
                employee.setUpdatedate(nowDate);
               int i= employeeMapper.updateByPrimaryKey(employee);
                if(i<=0){
                    throw new BizException("employeeテーブル更新は失敗しました");
                }
                GrantedHolidayLog log = new GrantedHolidayLog();
                log.setEmployeeId(employee.getId());
                log.setStatusCode(MasterClassCode.HOLIDAYLOGTYPE1.getCode());
                log.setDays(grantedHolidayForm.getGrantedDays());
                log.setCreateTime(nowDate);
               int j= grantedHolidayLogMapper.insertSelective(log);
               if(j<=0){
                   throw new BizException("LOGテーブル更新は失敗しました");
               }
                checkExpiryDays(grantedHolidayForm.getEmployeeId());
            }
        }catch (Exception e){
            throw  new BizException(e.getMessage());
        }
        return returnNum;
    }


    private  void checkExpiryDays(Long employeeId){
        List<GrantedHoliday> voList=  grantedHolidayMapper.getActiveDataByEmployeeId(employeeId);
        List<GrantedHoliday> expiryList = new ArrayList<>();
        List<GrantedHoliday> carryOverExpiryList = new ArrayList<>();
        if(Objects.nonNull(voList) && voList.size()>0){
            MasterClass masterClassExpiry= masterClassMapper.getByTypeAndCode(MasterClassCode.MASTER_TYPE1.getCode(),MasterClassCode.GHST04.getCode());
            MasterClass masterClassCarryOver= masterClassMapper.getByTypeAndCode(MasterClassCode.MASTER_TYPE1.getCode(),MasterClassCode.GHST03.getCode());

            if(Objects.isNull(masterClassExpiry) || Objects.isNull(masterClassCarryOver) ){
                throw new BizException("master code データはありません。");
            }
            UserInfo userInfo= UserInfoHolder.get();
            Date now = new Date();

            for (GrantedHoliday vo:voList) {
                if(vo.getCarryoverExpiryDate().getTime() < now.getTime()){
                    vo.setStatusCode(masterClassExpiry.getCode());
                    vo.setRemainingDays(0);
                    vo.setUpdateTime(now);
                    vo.setUserId(userInfo.getUserId());
                    expiryList.add(vo);
                }else if( vo.getExpiryDate().getTime() < now.getTime()){
                    vo.setStatusCode(masterClassCarryOver.getCode());
                    vo.setUpdateTime(now);
                    vo.setUserId(userInfo.getUserId());
                    carryOverExpiryList.add(vo);
                }
            }
        }
        if(expiryList.size()>0){
                grantedHolidayMapper.updateBatchSelective(expiryList);
        }
        if(carryOverExpiryList.size()>0){
            grantedHolidayMapper.updateBatchSelective(carryOverExpiryList);
        }
    }







    @Transactional
    @Override
    public int takeHoliday(long id, int days) {

        GrantedHoliday grantedHoliday = grantedHolidayMapper.selectByPrimaryKey(id);
        if(grantedHoliday.getUnusedDays() < days){
            throw new ApiException(ApiResponseCode.status_102,"申請した有給休暇日数は未消化日数より多い!!!");
        }

        try {
            int reduceCount = grantedHolidayMapper.reduceHoliday(id,days);

            if(reduceCount == 1){
                grantedHoliday = grantedHolidayMapper.selectByPrimaryKey(id);
                if(grantedHoliday.getRemainingDays() < 0){//
                    throw new ApiException(ApiResponseCode.status_103);
                } else if (grantedHoliday.getRemainingDays() == 0) {
                    //TODO [状態/status]カプセル化
                    int updateCount = grantedHolidayMapper.updateStatusById(id, "GHST05");//状態コード：消化済

                    if (updateCount == 1) {
                        return 1;
                    } else {//状態更新件数は1ではない場合、エラーになります
                        throw new ApiException(ApiResponseCode.status_103, "状態更新件数は1ではない!!!");
                    }
                } else {
                    return 1;
                }
            } else {//消化日数更新件数は1ではないの場合、エラーになります
                throw new ApiException(ApiResponseCode.status_103,"消化日数更新件数は1ではない!!!");
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ApiException(ApiResponseCode.status_103,e.getMessage());
        }
    }
}
