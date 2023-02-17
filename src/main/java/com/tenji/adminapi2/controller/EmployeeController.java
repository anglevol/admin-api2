package com.tenji.adminapi2.controller;

import com.tenji.adminapi2.api.ApiResponse;
import com.tenji.adminapi2.model.GrantedHolidayModel;
import com.tenji.adminapi2.service.GrantedHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
