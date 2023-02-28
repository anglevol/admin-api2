package com.tenji.adminapi2.service;

import com.tenji.adminapi2.dto.BasicQueryResult;
import com.tenji.adminapi2.dto.GrantedHolidayLogQueryForm;
import com.tenji.adminapi2.model.GrantedHolidayLog;

import java.util.List;

public interface GrantedHolidayLogService {

    BasicQueryResult getByEmployeeId(GrantedHolidayLogQueryForm queryForm);

}
