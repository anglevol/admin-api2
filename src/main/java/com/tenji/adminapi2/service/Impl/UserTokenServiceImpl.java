package com.tenji.adminapi2.service.Impl;

import com.tenji.adminapi2.mapper.UserTokenMapper;
import com.tenji.adminapi2.model.UserToken;
import com.tenji.adminapi2.service.UserTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public UserToken getByToken(String token) {
        return userTokenMapper.getByToken(token);
    }
}
