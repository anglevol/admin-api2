package com.tenji.adminapi2.service;

import com.tenji.adminapi2.dto.GrantedHolidayForm;
import com.tenji.adminapi2.model.GrantedHoliday;

import java.util.List;

public interface GrantedHolidayService {

    List<GrantedHoliday> getByEmployeeId(long employeeId);

    GrantedHoliday getById(long id);

    int add(GrantedHolidayForm grantedHolidayForm);

    int takeHoliday(long id, int days);

}
