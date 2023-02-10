package com.tenji.adminapi2.service.Impl;

import com.tenji.adminapi2.api.ApiResponseCode;
import com.tenji.adminapi2.dto.LoginUserVo;
import com.tenji.adminapi2.exception.ApiException;
import com.tenji.adminapi2.mapper.UserMapper;
import com.tenji.adminapi2.mapper.UserTokenMapper;
import com.tenji.adminapi2.model.User;
import com.tenji.adminapi2.model.UserToken;
import com.tenji.adminapi2.service.UserService;
import com.tenji.adminapi2.util.NanoIdUtils;
import com.tenji.adminapi2.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public User getById(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public LoginUserVo login(String userName, String password) {
        LoginUserVo vo =new LoginUserVo();
        User user=  userMapper.getUser(userName,password);
        if(user ==null){
            throw new ApiException(ApiResponseCode.status_103,"没有此用户");
        }
        String token =RandomUtil.getRandomStr(1)+"_"+ NanoIdUtils.randomNanoId32();

        UserToken userToken = userTokenMapper.getByUserId(user.getId());
        UserToken newUserToken = new UserToken();
        if(Objects.nonNull(userToken)){
            newUserToken.setUserId(user.getId());
            newUserToken.setToken(token);
           int i= userTokenMapper.updateById(newUserToken);
           if(i<=0){
               throw new ApiException(ApiResponseCode.status_103,"token 更新失败");
           }
        }else{
            newUserToken.setUserId(user.getId());
            newUserToken.setToken(token);
            int i= userTokenMapper.insert(newUserToken);
            if(i<=0){
                throw new ApiException(ApiResponseCode.status_103,"token 更新失败");
            }

        }
        vo.setUserId(user.getId());
        vo.setUserName(user.getUserName());
        vo.setToken(token);
        return vo;
    }


}
