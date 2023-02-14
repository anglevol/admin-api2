package com.tenji.adminapi2.controller;

import com.tenji.adminapi2.api.ApiResponse;
import com.tenji.adminapi2.model.GrantedHolidayModel;
import com.tenji.adminapi2.service.GrantedHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    GrantedHolidayService grantedHolidayService;

    @RequestMapping(value = "{employeeId}/grantedHolidays",method = RequestMethod.GET)
    public ApiResponse<List<GrantedHolidayModel>> getGrantedHolidayList(@PathVariable String employeeId) {
        List<GrantedHolidayModel> grantedHolidayModelList = grantedHolidayService.getByEmployeeId(employeeId);
        return new ApiResponse<>(grantedHolidayModelList);
    }

}
