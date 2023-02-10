package com.tenji.adminapi2.controller;

import com.tenji.adminapi2.api.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    //Add Employee 会社員を増加する
    @PostMapping(value="/add")
    public ApiResponse<String> addEmployee(Map<String, Object> map, HttpServletRequest request) {
        ApiResponse<String> result = new ApiResponse<String>("success");
        return result;
    }

    //Query Employee 会社員の資料を調べる
    @GetMapping(value="/add")
    public ApiResponse<Object> queryEmployee(HttpServletRequest request) {
        String employeeId = request.getParameter("employeeId");
        ApiResponse<Object> result = new ApiResponse<Object>("success");
        return result;
    }
}
