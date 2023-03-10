package com.tenji.adminapi2.controller;

import com.tenji.adminapi2.api.ApiResponse;


import com.tenji.adminapi2.dto.EmployeeDto;
import com.tenji.adminapi2.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import com.tenji.adminapi2.dto.EmployeeDto;
import com.tenji.adminapi2.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {

    @Resource
    EmployeeService  employeeService;
    //Add Employee 会社員を増加する
    @PostMapping(value = "/add", consumes = "application/json")
    public ApiResponse<String> addEmployee(@RequestBody EmployeeDto dto, HttpServletRequest request) {
        return employeeService.addEmployee(dto, request);
    }

    @GetMapping(value="/queryAll")
    public ApiResponse<Object> queryAll(HttpServletRequest request) {
        return employeeService.queryEmployee(request);
    }

    //Query Employee 会社員の資料を調べる
    @GetMapping(value="/query")
    public ApiResponse<Object> queryEmployee(HttpServletRequest request) {
        return employeeService.queryEmployee(request);
    }

    @GetMapping(value="/delete")
    public ApiResponse<Void> deleteEmployee(@RequestParam("id") Long id){
        employeeService.deleteEmployee(id);
        return new ApiResponse<>();
    }

    @PostMapping(value = "/update")
    public ApiResponse<Void> updateEmployee(@RequestBody EmployeeDto dto){
        employeeService.updateEmployee(dto);
        return new ApiResponse<>();
    }


}

