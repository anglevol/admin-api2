package com.tenji.adminapi2.mapper;

import com.tenji.adminapi2.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User getUser(@Param("userName") String userName,@Param("passWord") String passWord);
}
