package com.tenji.adminapi2.service.Impl;

import com.tenji.adminapi2.api.ApiResponseCode;
import com.tenji.adminapi2.dto.GrantedHolidayForm;
import com.tenji.adminapi2.exception.ApiException;
import com.tenji.adminapi2.mapper.GrantedHolidayMapper;
import com.tenji.adminapi2.entity.GrantedHoliday;
import com.tenji.adminapi2.model.GrantedHolidayModel;
import com.tenji.adminapi2.service.GrantedHolidayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class GrantedHolidayServiceImpl implements GrantedHolidayService {

    @Autowired
    GrantedHolidayMapper grantedHolidayMapper;

    @Override
    public List<GrantedHolidayModel> getByEmployeeId(String employeeId) {
        return grantedHolidayMapper.getByEmployeeId(employeeId);
    }

    @Override
    public GrantedHolidayModel getById(long id) {
        return grantedHolidayMapper.getById(id);
    }

    @Override
    public int add(GrantedHolidayForm grantedHolidayForm){
        GrantedHoliday grantedHoliday = new GrantedHoliday();
        grantedHoliday.setUserId(grantedHolidayForm.getUserId());
        grantedHoliday.setEmployeeId(grantedHolidayForm.getEmployeeId());
        grantedHoliday.setGrantedServiceYears(grantedHolidayForm.getGrantedServiceYears());
        grantedHoliday.setGrantedDate(grantedHolidayForm.getGrantedDate());
        grantedHoliday.setExpiryDate(grantedHolidayForm.getExpiryDate());
        grantedHoliday.setCarryoverExpiryDate(grantedHolidayForm.getCarryoverExpiryDate());
        grantedHoliday.setStatusCode(grantedHolidayForm.getStatusCode());
        grantedHoliday.setGrantedDays(grantedHolidayForm.getGrantedDays());
        grantedHoliday.setUsedDays(grantedHolidayForm.getUsedDays());
        grantedHoliday.setUnusedDays(grantedHolidayForm.getUnusedDays());
        grantedHoliday.setRemainingDays(grantedHolidayForm.getRemainingDays());

        Date createTime = new Date();
        grantedHoliday.setCreateTime(createTime);
        grantedHoliday.setUpdateTime(createTime);

        return grantedHolidayMapper.insert(grantedHoliday);
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
