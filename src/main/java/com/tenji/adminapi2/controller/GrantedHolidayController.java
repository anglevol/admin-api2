package com.tenji.adminapi2.controller;

import com.tenji.adminapi2.api.ApiResponse;
import com.tenji.adminapi2.dto.GrantedHolidayForm;
import com.tenji.adminapi2.model.GrantedHoliday;
import com.tenji.adminapi2.service.GrantedHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grantedHoliday")
public class GrantedHolidayController {

    @Autowired
    GrantedHolidayService grantedHolidayService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ApiResponse<GrantedHoliday> getGrantedHoliday(@PathVariable long id){

        GrantedHoliday grantedHoliday = grantedHolidayService.getById(id);
        return new ApiResponse<>(grantedHoliday);

    }

    @RequestMapping(value = "/add",method = RequestMethod.PUT)
    public ApiResponse<Integer> addGrantedHoliday(@RequestBody GrantedHolidayForm grantedHolidayForm){

        int row = grantedHolidayService.add(grantedHolidayForm);
        return new ApiResponse<>(row);

    }

    @RequestMapping(value = "changeStatus",method = RequestMethod.POST)
    public ApiResponse<Integer> changeStatusById(@RequestBody GrantedHolidayForm grantedHolidayForm){


        return new ApiResponse<>(1);

    }
}
