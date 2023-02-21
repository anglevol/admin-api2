package com.tenji.adminapi2.controller;

import com.tenji.adminapi2.api.ApiResponse;
import com.tenji.adminapi2.dto.GrantedHolidayForm;
import com.tenji.adminapi2.dto.TakeHolidayForm;
import com.tenji.adminapi2.model.GrantedHoliday;
import com.tenji.adminapi2.service.GrantedHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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

    @RequestMapping(value = "/list/{employeeId}",method = RequestMethod.GET)
    public ApiResponse<Object> getHolidayList(@PathVariable long employeeId){

        HashMap<String, Object> holidays = new HashMap<>();
        List<GrantedHoliday> grantedHolidays = grantedHolidayService.getByEmployeeId(employeeId);
        holidays.put("total", grantedHolidays.size());
        holidays.put("list", grantedHolidays);
        return new ApiResponse<>(holidays);

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
