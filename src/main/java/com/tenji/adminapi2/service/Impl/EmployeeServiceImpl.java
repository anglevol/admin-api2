package com.tenji.adminapi2.service.Impl;

import com.google.gson.Gson;
import com.tenji.adminapi2.api.ApiResponse;
import com.tenji.adminapi2.api.ApiResponseCode;
import com.tenji.adminapi2.dto.EmployeeDto;
import com.tenji.adminapi2.mapper.EmployeeMapper;
import com.tenji.adminapi2.model.Employee;
import com.tenji.adminapi2.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Resource
    EmployeeMapper employeeMapper;
    Gson gson = new Gson();
    public ApiResponse<String> addEmployee(EmployeeDto dto, HttpServletRequest request) {
        ApiResponse<String> result = new ApiResponse<String>("success");
        logger.info("入力されたdto:" + gson.toJson(dto));
        String operatorId = request.getParameter("operatorId");
        if(!"1".equals(operatorId)) {
            return result.code(ApiResponseCode.status_401.getCode()).message(ApiResponseCode.status_401.getMessage());
        }
        Employee employee = employeeMapper.selectByUserId(dto.getUserId());
        if(employee != null) {
            return result.code(ApiResponseCode.status_1.getCode()).message("社员已存在");
        }
        employee = employeeMapper.selectByName(dto.getName());
        if(employee != null) {
            return result.code(ApiResponseCode.status_1.getCode()).message("社员已存在");
        }
        Employee newEmployee = new Employee();
        BeanUtils.copyProperties(dto, newEmployee);
        newEmployee.setCreatedate(new Date());
        newEmployee.setUpdatedate(new Date());
        int i = employeeMapper.insert(newEmployee);
        return i == 1 ? result : result.code(ApiResponseCode.status_103.getCode()).message(ApiResponseCode.status_103.getMessage());
    }

    public ApiResponse<Object> queryEmployee(HttpServletRequest request) {
        String employeeId = request.getParameter("employeeId");
        Employee employee = employeeMapper.selectByEmployeeId(employeeId);
        ApiResponse<Object> result = new ApiResponse<Object>("success");
        return result.data(employee);
    }
}
