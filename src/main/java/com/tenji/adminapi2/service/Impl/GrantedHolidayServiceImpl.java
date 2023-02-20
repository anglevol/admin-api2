package com.tenji.adminapi2.service.Impl;

import com.tenji.adminapi2.api.ApiResponseCode;
import com.tenji.adminapi2.dto.GrantedHolidayForm;
import com.tenji.adminapi2.dto.GrantedHolidayVo;
import com.tenji.adminapi2.dto.UserInfo;
import com.tenji.adminapi2.dto.UserInfoHolder;
import com.tenji.adminapi2.dto.config.MasterClassCode;
import com.tenji.adminapi2.exception.ApiException;
import com.tenji.adminapi2.exception.BizException;
import com.tenji.adminapi2.mapper.EmployeeMapper;
import com.tenji.adminapi2.mapper.GrantedHolidayMapper;
import com.tenji.adminapi2.entity.GrantedHoliday;
import com.tenji.adminapi2.model.Employee;
import com.tenji.adminapi2.service.GrantedHolidayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class GrantedHolidayServiceImpl implements GrantedHolidayService {

    @Autowired
    GrantedHolidayMapper grantedHolidayMapper;

    @Autowired
    private   EmployeeMapper employeeMapper;

    @Override
    public List<GrantedHolidayVo> getByEmployeeId(Integer employeeId) {
        return grantedHolidayMapper.getByEmployeeId(employeeId);
    }

    @Override
    public GrantedHolidayVo getById(long id) {
        return grantedHolidayMapper.getById(id);
    }

    @Override
    public int add(GrantedHolidayForm grantedHolidayForm){

        Employee employee =employeeMapper.selectByEmployeeId(grantedHolidayForm.getEmployeeId());
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
            int year = aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR);

            grantedHoliday.setGrantedServiceYears(year);


            grantedHoliday.setStatusCode(MasterClassCode.GHST02.getCode());

            grantedHoliday.setGrantedDays(grantedHolidayForm.getGrantedDays());

            grantedHoliday.setUsedDays(0);
            grantedHoliday.setUnusedDays(grantedHolidayForm.getGrantedDays());
            grantedHoliday.setRemainingDays(grantedHolidayForm.getGrantedDays());

            grantedHoliday.setCreateTime(nowDate);
            grantedHoliday.setUpdateTime(nowDate);

            returnNum=  grantedHolidayMapper.insert(grantedHoliday);
            if(returnNum >0){

            }
        }catch (Exception e){
            throw  new BizException(e.getMessage());
        }
        return returnNum;
    }


    private  void checkExpiryDays(Integer employeeId){
        grantedHolidayMapper.getByEmployeeId(employeeId);


    }





    @Override
    public int updateStatus(long id, String statusCode) {
        return grantedHolidayMapper.updateStatus(id, statusCode);
    }

    @Transactional
    @Override
    public int takeHoliday(long id, int holiday) {

        GrantedHoliday grantedHoliday = grantedHolidayMapper.getEntityById(id);
        if(grantedHoliday.getUnusedDays() < holiday){
            throw new ApiException(ApiResponseCode.status_102,"申請した有給休暇日数は未消化日数より多い!!!");
        }

        try {
            int reduceCount = grantedHolidayMapper.reduceHoliday(id,holiday);

            if(reduceCount == 1){
                grantedHoliday = grantedHolidayMapper.getEntityById(id);
                if(grantedHoliday.getRemainingDays() < 0){//
                    throw new ApiException(ApiResponseCode.status_103);
                } else if (grantedHoliday.getRemainingDays() == 0) {
                    //TODO [状態/status]カプセル化
                    int updateCount = grantedHolidayMapper.updateStatus(id, "GHST05");//状態コード：消化済

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
