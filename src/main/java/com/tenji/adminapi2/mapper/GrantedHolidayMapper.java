package com.tenji.adminapi2.mapper;

import com.tenji.adminapi2.model.GrantedHoliday;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GrantedHolidayMapper {
    int deleteByPrimaryKey(int id);

    int insert(GrantedHoliday record);

    int insertSelective(GrantedHoliday record);

    GrantedHoliday selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(GrantedHoliday record);

    int updateByPrimaryKey(GrantedHoliday record);

    List<GrantedHoliday> selectByEmployeeId(long employeeId);

    int reduceHoliday(long id, int days);

    int updateStatusById(long id, String statusCode);

    List<GrantedHoliday> getActiveDataByEmployeeId(@Param("employeeId") Long employeeId);



    int updateBatchSelective(List<GrantedHoliday> list);


    Integer countActiveDays(@Param("employeeId") Long employeeId);


}
