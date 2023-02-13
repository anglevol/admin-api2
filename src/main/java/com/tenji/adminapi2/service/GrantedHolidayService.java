package com.tenji.adminapi2.service;


import com.tenji.adminapi2.dto.GrantedHolidayForm;
import com.tenji.adminapi2.model.GrantedHoliday;

import java.util.List;

public interface GrantedHolidayService {

    List<GrantedHoliday> getByUserId(long userId);

    GrantedHoliday getById(long id);

    int add(GrantedHolidayForm grantedHolidayForm);

    int changeStatusById(long id,int status);

}
