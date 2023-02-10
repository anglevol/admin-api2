package com.tenji.adminapi2.mapper;

import com.tenji.adminapi2.model.GrantedHoliday;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GrantedHolidayMapper {

    GrantedHoliday getGrantedHolidayByUserId(@Param("userId") long userId);

}
