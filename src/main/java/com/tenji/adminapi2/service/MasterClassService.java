package com.tenji.adminapi2.service;

import com.tenji.adminapi2.entity.MasterClass;
import com.tenji.adminapi2.model.MasterClassModel;

import java.util.List;

public interface MasterClassService {

    List<MasterClassModel> getByType(String type);

    int add(MasterClass masterClass);

}
