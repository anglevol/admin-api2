package com.tenji.adminapi2.controller;

import com.tenji.adminapi2.api.ApiResponse;
import com.tenji.adminapi2.dto.GrantedHolidayForm;
import com.tenji.adminapi2.dto.TakeHolidayForm;
import com.tenji.adminapi2.model.GrantedHolidayModel;
import com.tenji.adminapi2.service.GrantedHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/granted")
public class GrantedHolidayController {

    @Autowired
    GrantedHolidayService grantedHolidayService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ApiResponse<GrantedHolidayModel> getGrantedHoliday(@PathVariable long id){

        GrantedHolidayModel grantedHolidayModel = grantedHolidayService.getById(id);
        return new ApiResponse<>(grantedHolidayModel);

    }

    @RequestMapping(value = "/add",method = RequestMethod.PUT)
    public ApiResponse<Integer> addGrantedHoliday(@RequestBody GrantedHolidayForm grantedHolidayForm){

        int row = grantedHolidayService.add(grantedHolidayForm);
        return new ApiResponse<>(row);

    }

    @RequestMapping(value = "/changeStatus",method = RequestMethod.POST)
    public ApiResponse<Integer> changeStatus(@RequestBody GrantedHolidayForm grantedHolidayForm){

        int updateRow = grantedHolidayService.updateStatus(grantedHolidayForm.getId(),grantedHolidayForm.getStatusCode());
        return new ApiResponse<>(updateRow);

    }

    @RequestMapping(value = "/takeHoliday",method = RequestMethod.POST)
    public ApiResponse<Integer> takeHoliday(@RequestBody TakeHolidayForm takeHolidayForm){

        int updateRow = grantedHolidayService.takeHoliday(takeHolidayForm.getId(),takeHolidayForm.getHoliday());
        return new ApiResponse<>(updateRow);

    }
}
