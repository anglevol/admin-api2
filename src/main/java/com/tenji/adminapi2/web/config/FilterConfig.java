package com.tenji.adminapi2.web.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>();
        bean.setFilter(new CorsFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(1);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<SignValidFilter> signValidFilterRegistrationBean() {
        FilterRegistrationBean<SignValidFilter> bean = new FilterRegistrationBean<SignValidFilter>();
        bean.setFilter(new SignValidFilter());
        bean.addUrlPatterns("/*");
        bean.addInitParameter("exclusions",
                "/swagger-resources," +
                        "/webjars," +
                        "/v2/api-docs," +
                        "/swagger-ui.html," +
                        "/doc.html," +
                        "/v1/base/health/healthCheck,"+
                        "/v1/wxNotify/payNotify2Xml,"+
                        "/v1/wxNotify/refundNotify2Xml,"+
                        "/v1/wx/miniprogram/getAccessToken"
        );
        bean.setOrder(2);
        return bean;
    }


}
