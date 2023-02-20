package com.tenji.adminapi2.controller;

import com.tenji.adminapi2.api.ApiResponse;
import com.tenji.adminapi2.dto.MasterClassVo;
import com.tenji.adminapi2.service.MasterClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/masterclass")
public class MasterClassController {

    @Autowired
    MasterClassService masterClassService;

    @RequestMapping(value = "/type/{type}",method = RequestMethod.GET)
    public ApiResponse<List<MasterClassVo>> getMasterClassByType(@PathVariable String type){
        List<MasterClassVo> masterClassVos = masterClassService.getByType(type);
        return new ApiResponse<>(masterClassVos);
    }

}
