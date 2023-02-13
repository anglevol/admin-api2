package com.tenji.adminapi2.mapper;

import com.tenji.adminapi2.model.GrantedHoliday;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GrantedHolidayMapper {

    List<GrantedHoliday> getByUserId(@Param("userId") long userId);

    GrantedHoliday getById(@Param("id") long id);

    int insert(GrantedHoliday grantedHoliday);

    int updateStatusById(long id,int status);

}
