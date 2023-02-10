package com.tenji.adminapi2.web.config;

import com.tenji.adminapi2.web.interceptor.UserTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public UserTokenInterceptor userTokenInterceptor() {
        return new UserTokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        InterceptorRegistration registration = registry.addInterceptor(userTokenInterceptor());
        // 所有路径都被拦截
        registration.addPathPatterns("/**");
        // 添加不拦截路径
        registration.excludePathPatterns(
                "/index/index",
                "/index/login"
        );
    }
}
