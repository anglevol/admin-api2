package com.tenji.adminapi2.controller;

import com.tenji.adminapi2.api.ApiResponse;
import com.tenji.adminapi2.dto.GrantedHolidayForm;
import com.tenji.adminapi2.dto.GrantedHolidayVo;
import com.tenji.adminapi2.dto.TakeHolidayForm;
import com.tenji.adminapi2.model.GrantedHoliday;
import com.tenji.adminapi2.service.GrantedHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/granted")
@CrossOrigin
public class GrantedHolidayController {

    @Autowired
    GrantedHolidayService grantedHolidayService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ApiResponse<GrantedHoliday> getGrantedHoliday(@PathVariable long id){

        GrantedHoliday grantedHoliday = grantedHolidayService.getById(id);
        return new ApiResponse<>(grantedHoliday);

    }


    @PostMapping("/add")
    public ApiResponse<Integer> addGrantedHoliday(@RequestBody GrantedHolidayForm grantedHolidayForm){
        int row = grantedHolidayService.add(grantedHolidayForm);
        return new ApiResponse<>(row);

    }

    @RequestMapping(value = "/takeHoliday",method = RequestMethod.POST)
    public ApiResponse<Integer> takeHoliday(@RequestBody TakeHolidayForm takeHolidayForm){

        int updateRow = grantedHolidayService.takeHoliday(takeHolidayForm.getId(),takeHolidayForm.getHoliday());
        return new ApiResponse<>(updateRow);

    }
}
