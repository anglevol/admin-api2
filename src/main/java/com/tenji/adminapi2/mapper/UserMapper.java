package com.tenji.adminapi2.mapper;

import com.tenji.adminapi2.dto.UserSearchForm;
import com.tenji.adminapi2.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User getUser(@Param("userName") String userName,@Param("passWord") String passWord);

    User getById(Long id);

    List<User> getUserList(UserSearchForm form);

}
