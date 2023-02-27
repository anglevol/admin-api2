package com.tenji.adminapi2.service;

import com.tenji.adminapi2.model.MasterClass;
import com.tenji.adminapi2.dto.MasterClassVo;

import java.util.List;

public interface MasterClassService {

    List<MasterClassVo> getByType(String type);

    int add(MasterClass masterClass);

}
