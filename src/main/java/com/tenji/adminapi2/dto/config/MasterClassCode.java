package com.tenji.adminapi2.dto.config;

public enum MasterClassCode {

    GHST01("未開始", ""),
    GHST02("有効", ""),
    GHST03("繰越", ""),
    GHST04("失効", ""),
    GHST05("消化済", ""),
    MASTER_TYPE1("GHST",""),
    MASTER_TYPE2("DPT","");

    private String code;

    private String message;

    private MasterClassCode(String code ,String message){
        this.code=code;

        this.message=message;
    }

    public String getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
