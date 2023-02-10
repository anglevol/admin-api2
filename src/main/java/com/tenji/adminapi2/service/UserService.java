package com.tenji.adminapi2.service;

import com.tenji.adminapi2.dto.LoginUserVo;
import com.tenji.adminapi2.model.User;

public interface UserService {
    User getById(Long id);

    LoginUserVo login(String userName,String password);

}
