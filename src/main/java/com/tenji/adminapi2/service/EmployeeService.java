package com.tenji.adminapi2.service;

import com.tenji.adminapi2.api.ApiResponse;
import com.tenji.adminapi2.dto.EmployeeDto;

import javax.servlet.http.HttpServletRequest;


import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;


public interface EmployeeService {

    @Bean
    ApiResponse<String> addEmployee(EmployeeDto dto, HttpServletRequest request);
}