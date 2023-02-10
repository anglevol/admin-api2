package com.tenji.adminapi2.controller;

import com.tenji.adminapi2.dto.LoginFrom;
import com.tenji.adminapi2.mapper.UserMapper;
import com.tenji.adminapi2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String Index(){
        return "success";
    }


    @PostMapping("/login")
    public String Login(@RequestBody LoginFrom from){

       User user= userMapper.getUser(from.getUserName(),from.getPassWord());

        return "success-----"+user.getUserName()+"------"+user.getPassWord()+"--------------"+user.getToken();
    }



}
