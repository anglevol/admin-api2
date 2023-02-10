package com.tenji.adminapi2.service;

import com.tenji.adminapi2.model.UserToken;

public interface UserTokenService {
    UserToken getByToken(String token);
}

