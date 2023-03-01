package com.tenji.adminapi2.mapper;

import com.tenji.adminapi2.model.MasterClass;
import com.tenji.adminapi2.dto.MasterClassVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MasterClassMapper {

    List<MasterClass> getByType(@Param("type") String type);

    int insert(MasterClass masterClass);

    MasterClass getByTypeAndCode(@Param("type") String type,@Param("code") String code);
}
