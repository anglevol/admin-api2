package com.tenji.adminapi2.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.tenji.adminapi2.api.ApiResponse;
import com.tenji.adminapi2.api.ApiResponseCode;
import com.tenji.adminapi2.dto.EmployeeDto;
import com.tenji.adminapi2.dto.UserInfo;
import com.tenji.adminapi2.dto.UserInfoHolder;

import com.tenji.adminapi2.mapper.EmployeeMapper;
import com.tenji.adminapi2.model.Employee;
import com.tenji.adminapi2.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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
        UserInfo userInfo= UserInfoHolder.get();
       Long userId= userInfo.getUserId();
        if(userId !=1) {
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
        employee = employeeMapper.selectByEmployeeId(dto.getEmployeeid());
        if(employee != null) {
            return result.code(ApiResponseCode.status_1.getCode()).message("社员已存在");
        }
        Employee newEmployee = new Employee();
        BeanUtils.copyProperties(dto, newEmployee);
        newEmployee.setUserId(userId.intValue());
        newEmployee.setCreatedate(new Date());
        newEmployee.setUpdatedate(new Date());
        int i = employeeMapper.insertSelective(newEmployee);
        newEmployee.setEmployeeid("TJE_"+i);
        int j=  employeeMapper.updateByPrimaryKey(newEmployee);
        return j == 1 ? result : result.code(ApiResponseCode.status_103.getCode()).message(ApiResponseCode.status_103.getMessage());
    }

    public ApiResponse<Object> queryEmployee(HttpServletRequest request) {
        String id = request.getParameter("id");
        ApiResponse<Object> result = new ApiResponse<Object>("success");
        if(StringUtils.isEmpty(id)) {
            String num = request.getParameter("pageNum");
            String size = request.getParameter("pageSize");
            String searchWord = request.getParameter("searchWord");
            int pageNum = StringUtils.isEmpty(num) ? 1 : Integer.valueOf(num);
            int pageSize = StringUtils.isEmpty(size) ? 10 : Integer.valueOf(size);
            PageHelper.startPage(pageNum, pageSize);
            List<Employee> employeeList = employeeMapper.selectAll(searchWord);
            PageInfo pageInfo = new PageInfo(employeeList);
            return result.data(pageInfo);
        }
        Employee employee = employeeMapper.selectByPrimaryKey(Long.parseLong(id));
        return result.data(employee);
    }
}
