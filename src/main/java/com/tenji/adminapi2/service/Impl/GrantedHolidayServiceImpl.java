package com.tenji.adminapi2.service.Impl;

import com.tenji.adminapi2.dto.GrantedHolidayForm;
import com.tenji.adminapi2.mapper.GrantedHolidayMapper;
import com.tenji.adminapi2.model.GrantedHoliday;
import com.tenji.adminapi2.service.GrantedHolidayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class GrantedHolidayServiceImpl implements GrantedHolidayService {

    @Autowired
    GrantedHolidayMapper grantedHolidayMapper;

    @Override
    public List<GrantedHoliday> getByUserId(long userId) {
        return grantedHolidayMapper.getByUserId(userId);
    }

    @Override
    public GrantedHoliday getById(long id) {
        return grantedHolidayMapper.getById(id);
    }

    @Override
    public int add(GrantedHolidayForm grantedHolidayForm){
        GrantedHoliday grantedHoliday = new GrantedHoliday();
        grantedHoliday.setUserId(grantedHolidayForm.getUserId());
        grantedHoliday.setGrantedServiceYears(grantedHolidayForm.getGrantedServiceYears());
        grantedHoliday.setGrantedDate(grantedHolidayForm.getGrantedDate());
        grantedHoliday.setExpiryDate(grantedHolidayForm.getExpiryDate());
        grantedHoliday.setCarryoverExpiryDate(grantedHolidayForm.getCarryoverExpiryDate());
        grantedHoliday.setStatus(grantedHolidayForm.getStatus());
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
    public int changeStatusById(long id, int status) {
        return grantedHolidayMapper.updateStatusById(id,status);
    }
}
