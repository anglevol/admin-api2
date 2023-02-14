package com.tenji.adminapi2.controller;

import com.tenji.adminapi2.api.ApiResponse;
import com.tenji.adminapi2.dto.UserInfo;
import com.tenji.adminapi2.dto.UserInfoHolder;
import com.tenji.adminapi2.model.User;
import com.tenji.adminapi2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/userInfo")
    public ApiResponse<User> getUserInfo(){
        UserInfo userInfo= UserInfoHolder.get();
        User user=   userService.getById(userInfo.getUserId());
        return new ApiResponse<>(user);
    }
}
