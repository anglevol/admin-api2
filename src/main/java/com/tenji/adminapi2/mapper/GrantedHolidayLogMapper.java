package com.tenji.adminapi2.mapper;

import com.tenji.adminapi2.model.GrantedHolidayLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GrantedHolidayLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GrantedHolidayLog record);

    int insertSelective(GrantedHolidayLog record);

    GrantedHolidayLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GrantedHolidayLog record);

    int updateByPrimaryKey(GrantedHolidayLog record);
}
