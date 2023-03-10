package com.tenji.adminapi2.mapper;

import com.tenji.adminapi2.dto.GrantedHolidayVo;
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

    List<GrantedHolidayVo> selectByEmployeeId(long employeeId, int page, int size);

    long selectTotolsByEmployeeId(long employeeId);

    int reduceHoliday(@Param("id") long id, @Param("days") int days);

    int updateStatusById(@Param("id") long id, @Param("statusCode") String statusCode);

    List<GrantedHoliday> getActiveDataByEmployeeId(@Param("employeeId") Long employeeId);



    int updateBatchSelective(List<GrantedHoliday> list);


    Integer countActiveDays(@Param("employeeId") Long employeeId);


}
