package com.tenji.adminapi2.mapper;

import com.tenji.adminapi2.model.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    Employee selectByUserId(Integer userId);

    Employee selectByName(String name);

    Employee selectByEmployeeId(String employeeId);

    List<Employee> selectAll();
}
