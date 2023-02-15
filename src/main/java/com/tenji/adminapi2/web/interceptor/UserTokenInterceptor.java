package com.tenji.adminapi2.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.tenji.adminapi2.api.ApiResponseCode;
import com.tenji.adminapi2.dto.UserInfo;
import com.tenji.adminapi2.dto.UserInfoHolder;
import com.tenji.adminapi2.exception.ApiException;
import com.tenji.adminapi2.model.User;
import com.tenji.adminapi2.model.UserToken;
import com.tenji.adminapi2.service.UserService;
import com.tenji.adminapi2.service.UserTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
public class UserTokenInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private UserTokenService userTokenService;

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求携带的令牌
       // String token = request.getHeader("UserToken");
        String token = request.getParameter("token");

        if (StringUtils.isNotBlank(token)) {
            UserToken userToken = userTokenService.getByToken(token);
            if (Objects.nonNull(userToken)) {
                User user =userService.getById(userToken.getUserId());
                if(Objects.nonNull(user)){
                    UserInfo userInfo = new UserInfo();
                    userInfo.setUserId(userToken.getUserId());
                    userInfo.setUserName(user.getUserName());
                    UserInfoHolder.add(userInfo);
                    return true;
                }else{
                    throw new ApiException(ApiResponseCode.status_304,"用户不存在");
                }
            } else {
                throw new ApiException(ApiResponseCode.status_304,"用户不存在");
            }
        } else {
            log.info("request: " + request.getRequestURI());
            throw new ApiException(ApiResponseCode.status_304,"请正确传值");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除用户信息
        UserInfoHolder.remove();
        return;
    }
}
