package com.tenji.adminapi2.service;

import com.tenji.adminapi2.dto.BasicQueryResult;
import com.tenji.adminapi2.dto.GrantedHolidayForm;
import com.tenji.adminapi2.dto.GrantedHolidayQueryForm;
import com.tenji.adminapi2.model.GrantedHoliday;

public interface GrantedHolidayService {

    BasicQueryResult getGrantedHolidayVoListByEmployeeId(GrantedHolidayQueryForm queryForm);

    GrantedHoliday getById(long id);

    int add(GrantedHolidayForm grantedHolidayForm);

    int takeHoliday(long id, int days);

}
