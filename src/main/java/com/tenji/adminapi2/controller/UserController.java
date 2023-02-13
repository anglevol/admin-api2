package com.tenji.adminapi2.controller;

import com.tenji.adminapi2.api.ApiResponse;
import com.tenji.adminapi2.model.GrantedHoliday;
import com.tenji.adminapi2.service.GrantedHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    GrantedHolidayService grantedHolidayService;

    @RequestMapping(value = "/{userId}/grantedHoliday",method = RequestMethod.GET)
    public ApiResponse<List<GrantedHoliday>> getGrantedHolidayList(@PathVariable long userId){

        List<GrantedHoliday> grantedHolidays = grantedHolidayService.getByUserId(userId);
        return new ApiResponse<>(grantedHolidays);

    }

}
