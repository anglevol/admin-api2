package com.tenji.adminapi2.controller;

import com.tenji.adminapi2.api.ApiResponse;
import com.tenji.adminapi2.dto.EmployeeDto;
import com.tenji.adminapi2.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    EmployeeService  employeeService;
    //Add Employee 会社員を増加する
    @PostMapping(value = "/add", consumes = "application/json")
    public ApiResponse<String> addEmployee(@RequestBody EmployeeDto dto, HttpServletRequest request) {
        return employeeService.addEmployee(dto, request);
    }
}