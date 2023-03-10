package com.tenji.adminapi2.dto.config;

public enum MasterClassCode {

    GHST01("GHST01", "未開始"),
    GHST02("GHST02", "有効"),
    GHST03("GHST03", "繰越"),
    GHST04("GHST04", "失効"),
    GHST05("GHST05", "消化済"),
    MASTER_TYPE1("GHST",""),
    MASTER_TYPE2("DPT",""),
    HOLIDAYLOGTYPE1("1","付与"),
    HOLIDAYLOGTYPE2("2","消化"),
    DELETEDFLAG_N("0","普通"),
    DELETEDFLAG_D("1","削除"),
    GENDERMAN("0","男"),
    GENDERWOMEN("1","女");

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
