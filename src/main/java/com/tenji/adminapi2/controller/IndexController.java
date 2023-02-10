package com.tenji.adminapi2.controller;

import com.tenji.adminapi2.api.ApiResponse;
import com.tenji.adminapi2.dto.LoginFrom;
import com.tenji.adminapi2.dto.LoginUserVo;
import com.tenji.adminapi2.mapper.UserMapper;
import com.tenji.adminapi2.model.User;
import com.tenji.adminapi2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public ApiResponse<String> Index(){

        return new ApiResponse<>("success");
    }


    @PostMapping("/login")
    public ApiResponse<LoginUserVo> Login(@RequestBody LoginFrom from){

        LoginUserVo vo= userService.login(from.getUserName(),from.getPassWord());

       return new ApiResponse<>(vo);
    }



}
