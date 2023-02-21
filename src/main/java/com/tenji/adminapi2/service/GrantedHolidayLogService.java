package com.tenji.adminapi2.service;

import com.tenji.adminapi2.model.GrantedHolidayLog;

import java.util.List;

public interface GrantedHolidayLogService {

    List<GrantedHolidayLog> getByEmployeeId(long employeeId);

}
