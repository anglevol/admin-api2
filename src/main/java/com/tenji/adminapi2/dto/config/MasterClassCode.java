package com.tenji.adminapi2.dto.config;

public enum MasterClassCode {

    MASTER_TYPE1("GHST",""),
    MASTER_TYPE2("DPT",""),
    MASTER_TYPE3("PROC",""),

    GHST01("GHST01", "未開始"),
    GHST02("GHST02", "有効"),
    GHST03("GHST03", "繰越"),
    GHST04("GHST04", "失効"),
    GHST05("GHST05", "消化済"),

    HOLIDAYLOGTYPE1("PROC01","付与"),
    HOLIDAYLOGTYPE2("PROC02","消化");

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
