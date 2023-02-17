package com.tenji.adminapi2.mapper;

import com.tenji.adminapi2.entity.MasterClass;
import com.tenji.adminapi2.model.MasterClassModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MasterClassMapper {

    List<MasterClassModel> getByType(@Param("type") String type);

    int insert(MasterClass masterClass);

}
