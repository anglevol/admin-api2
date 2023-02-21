package com.tenji.adminapi2.service.Impl;

import com.tenji.adminapi2.mapper.GrantedHolidayLogMapper;
import com.tenji.adminapi2.model.GrantedHolidayLog;
import com.tenji.adminapi2.service.GrantedHolidayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrantedHolidayLogServiceImpl implements GrantedHolidayLogService {

    @Autowired
    GrantedHolidayLogMapper grantedHolidayLogMapper;


    @Override
    public List<GrantedHolidayLog> getByEmployeeId(long employeeId) {
        return grantedHolidayLogMapper.selectByEmployeeId(employeeId);
    }
}
