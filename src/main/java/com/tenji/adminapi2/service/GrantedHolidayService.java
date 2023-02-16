package com.tenji.adminapi2.service;

import com.tenji.adminapi2.dto.GrantedHolidayForm;
import com.tenji.adminapi2.model.GrantedHolidayModel;

import java.util.List;

public interface GrantedHolidayService {

    List<GrantedHolidayModel> getByEmployeeId(String employeeId);

    GrantedHolidayModel getById(long id);

    int add(GrantedHolidayForm grantedHolidayForm);

    int updateStatus(long id, String statusCode);

    int takeHoliday(long id, int holiday);

}
