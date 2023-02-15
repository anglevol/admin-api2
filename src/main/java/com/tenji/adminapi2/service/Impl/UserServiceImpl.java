package com.tenji.adminapi2.service.Impl;


import com.tenji.adminapi2.api.ApiResponseCode;
import com.tenji.adminapi2.dto.LoginUserVo;
import com.tenji.adminapi2.dto.UserSearchForm;
import com.tenji.adminapi2.exception.ApiException;
import com.tenji.adminapi2.exception.BizException;
import com.tenji.adminapi2.mapper.UserMapper;
import com.tenji.adminapi2.mapper.UserTokenMapper;
import com.tenji.adminapi2.model.User;
import com.tenji.adminapi2.model.UserToken;
import com.tenji.adminapi2.service.UserService;
import com.tenji.adminapi2.util.NanoIdUtils;
import com.tenji.adminapi2.util.RandomUtil;
import com.tenji.adminapi2.web.config.TenjiProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.nio.charset.StandardCharsets;

import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TenjiProperties tenjiProperties;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public User getById(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public LoginUserVo login(String userName, String password)  {
        LoginUserVo vo =new LoginUserVo();
        try{
            String pwdEncryption = DigestUtils.md5DigestAsHex((password+tenjiProperties.getPwdEncryption()).getBytes(StandardCharsets.UTF_8)) ;

            User user=  userMapper.getUser(userName,pwdEncryption);
            if(user ==null){
                throw new BizException("没有此用户");
            }
            String token =RandomUtil.getRandomStr(1)+"_"+ NanoIdUtils.randomNanoId32();

            UserToken userToken = userTokenMapper.getByUserId(user.getId());
            UserToken newUserToken = new UserToken();
            if(Objects.nonNull(userToken)){
                newUserToken.setUserId(user.getId());
                newUserToken.setToken(token);
                int i= userTokenMapper.updateById(newUserToken);
                if(i<=0){
                    throw new BizException("token 更新失败");
                }
            }else{
                newUserToken.setUserId(user.getId());
                newUserToken.setToken(token);
                int i= userTokenMapper.insert(newUserToken);
                if(i<=0){
                    throw new BizException("token 更新失败");
                }

            }
            vo.setUserId(user.getId());
            vo.setUserName(user.getUserName());
            vo.setToken(token);

        }catch (Exception  e){
            throw new BizException(e.getMessage());
        }
        return vo;

    }




}
