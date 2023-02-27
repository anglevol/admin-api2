package com.tenji.adminapi2.service.Impl;

import com.tenji.adminapi2.model.MasterClass;
import com.tenji.adminapi2.mapper.MasterClassMapper;
import com.tenji.adminapi2.dto.MasterClassVo;
import com.tenji.adminapi2.service.MasterClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterClassServiceImpl implements MasterClassService {

    @Autowired
    MasterClassMapper masterClassMapper;

    @Override
    public List<MasterClassVo> getByType(String type) {
        return masterClassMapper.getByType(type);
    }

    @Override
    public int add(MasterClass masterClass) {
        return masterClassMapper.insert(masterClass);
    }
}
