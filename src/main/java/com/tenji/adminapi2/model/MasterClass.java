package com.tenji.adminapi2.model;

import lombok.Data;

@Data
public class MasterClass {
    //ID
    private long id;
    //種類
    private String type;
    //種類和名
    private String typeName;
    //コード
    private String code;
    //バリュー
    private String value;
}
