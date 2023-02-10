package com.tenji.adminapi2.mapper;

import com.tenji.adminapi2.model.UserToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserTokenMapper {
    UserToken getByToken(@Param("token") String token);

    UserToken getByUserId(@Param("userId") Long userId);

    int updateById(UserToken userToken);

    int insert(UserToken userToken);
}
