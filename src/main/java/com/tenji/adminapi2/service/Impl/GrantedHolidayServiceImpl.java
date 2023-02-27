package com.tenji.adminapi2.service.Impl;

import com.tenji.adminapi2.api.ApiResponseCode;
import com.tenji.adminapi2.dto.*;
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

    /**
     * フォームから社員ID、ページ、表示件数を取得して、相応ページの付与一覧データを画面に渡します
     * @param queryForm
     * @return
     */
    @Override
    public BasicQueryResult getGrantedHolidayVoListByEmployeeId(GrantedHolidayQueryForm queryForm) {

        int currentPage = queryForm.getPageNum();
        int pageSize = queryForm.getPageSize();
        long employeeId = queryForm.getEmployeeId();

        List <GrantedHolidayVo> holidayVoList = grantedHolidayMapper.selectByEmployeeId(employeeId, (currentPage-1)*pageSize, pageSize);
        long totals = grantedHolidayMapper.selectTotolsByEmployeeId(queryForm.getEmployeeId());

        BasicQueryResult queryResult = new BasicQueryResult();
        queryResult.setPageNum(queryForm.getPageNum());
        queryResult.setPageSize(queryForm.getPageSize());
        queryResult.setTotalSize(totals);
        queryResult.setResult(holidayVoList);

        return queryResult;
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
        if(grantedHoliday.getRemainingDays() < days){
            throw new BizException("申請日数は残日数より多い!!!");
        }

        try {
            int reduceCount = grantedHolidayMapper.reduceHoliday(id,days);

            if(reduceCount == 1){
                grantedHoliday = grantedHolidayMapper.selectByPrimaryKey(id);

                if (grantedHoliday.getRemainingDays() == 0) {
                    int updateCount = grantedHolidayMapper.updateStatusById(id, MasterClassCode.GHST05.getCode());//状態コード：消化済
                    if (updateCount != 1) {//状態更新件数は1ではない場合、エラーになります
                        throw new BizException("状態更新件数は異常です!!!");
                    }
                }

                GrantedHolidayLog log = new GrantedHolidayLog();
                log.setEmployeeId(grantedHoliday.getEmployeeId());
                log.setStatusCode(MasterClassCode.HOLIDAYLOGTYPE2.getCode());
                log.setDays(days);
                log.setCreateTime(new Date());
                int insertCount = grantedHolidayLogMapper.insertSelective(log);

                if(insertCount != 1){
                    throw new BizException("付与履歴テーブルの更新は失敗しました!!!");
                }

            } else {//消化日数更新件数は1ではないの場合、エラーになります
                throw new BizException("未消化、消化、残日数更新件数は異常です!!!");
            }

            return 1;

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ApiException(ApiResponseCode.status_103,e.getMessage());
        }
    }
}
