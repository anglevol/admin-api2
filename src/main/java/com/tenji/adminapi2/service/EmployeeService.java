package com.tenji.adminapi2.service;

import com.tenji.adminapi2.api.ApiResponse;
import com.tenji.adminapi2.dto.EmployeeDto;

import javax.servlet.http.HttpServletRequest;

public interface EmployeeService {
    ApiResponse<String> addEmployee(EmployeeDto dto, HttpServletRequest request);

    ApiResponse<Object> queryEmployee(HttpServletRequest request);

}
