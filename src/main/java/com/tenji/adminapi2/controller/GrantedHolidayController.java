package com.tenji.adminapi2.controller;

import com.tenji.adminapi2.api.ApiResponse;
import com.tenji.adminapi2.dto.*;
import com.tenji.adminapi2.model.GrantedHoliday;
import com.tenji.adminapi2.model.GrantedHolidayLog;
import com.tenji.adminapi2.service.GrantedHolidayLogService;
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

    @Autowired
    GrantedHolidayLogService grantedHolidayLogService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ApiResponse<GrantedHoliday> getGrantedHolidayById(@PathVariable long id){

        GrantedHoliday grantedHoliday = grantedHolidayService.getById(id);
        return new ApiResponse<>(grantedHoliday);

    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ApiResponse<BasicQueryResult> getHolidayListByEmployeeId(GrantedHolidayQueryForm queryForm){
        BasicQueryResult queryResult = grantedHolidayService.getGrantedHolidayVoListByEmployeeId(queryForm);
        return new ApiResponse<>(queryResult);
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

    @RequestMapping(value = "/logs/{employeeId}",method = RequestMethod.GET)
    public ApiResponse<Object> getHolidayLogByEmployeeId(@PathVariable long employeeId){

        HashMap<String, Object> logs = new HashMap<>();
        List<GrantedHolidayLog> holidayLogs = grantedHolidayLogService.getByEmployeeId(employeeId);
        logs.put("total", holidayLogs.size());
        logs.put("list", holidayLogs);
        return new ApiResponse<>(logs);

    }
}
