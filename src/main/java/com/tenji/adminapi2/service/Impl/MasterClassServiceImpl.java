package com.tenji.adminapi2.service.Impl;

import com.tenji.adminapi2.exception.BizException;
import com.tenji.adminapi2.model.MasterClass;
import com.tenji.adminapi2.mapper.MasterClassMapper;
import com.tenji.adminapi2.dto.MasterClassVo;
import com.tenji.adminapi2.service.MasterClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MasterClassServiceImpl implements MasterClassService {

    @Autowired
    MasterClassMapper masterClassMapper;

    @Override
    public List<MasterClassVo> getByType(String type) {
        List<MasterClassVo> voList= new ArrayList<>();
        List<MasterClass> masterClassList=masterClassMapper.getByType(type);
        if(Objects.isNull(masterClassList)|| masterClassList.size()==0){
            throw new BizException("システムエーラーです、DB設定のデータはありません");
        }
        for (MasterClass masterClass : masterClassList) {
            MasterClassVo vo= new MasterClassVo();
            vo.setType(masterClass.getType());
            vo.setTypeName(masterClass.getTypeName());
            vo.setCode(masterClass.getCode());
            vo.setValue(masterClass.getValue());
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public int add(MasterClass masterClass) {
        return masterClassMapper.insert(masterClass);
    }
}
