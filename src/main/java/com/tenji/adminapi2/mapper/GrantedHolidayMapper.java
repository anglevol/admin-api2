package com.tenji.adminapi2.mapper;

import com.tenji.adminapi2.entity.GrantedHoliday;
import com.tenji.adminapi2.model.GrantedHolidayModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GrantedHolidayMapper {

    List<GrantedHolidayModel> getByEmployeeId(@Param("employeeId") String employeeId);

    List<GrantedHolidayModel> getByStatusCode(@Param("statusCode") String statusCode);

    GrantedHolidayModel getById(@Param("id") long id);

    GrantedHoliday getEntityById(@Param("id") long id);

    int insert(GrantedHoliday grantedHoliday);

    int updateStatus(@Param("id") long id, @Param("statusCode") String statusCode);

    int reduceHoliday(@Param("id") long id, @Param("holiday")int holiday);

}
