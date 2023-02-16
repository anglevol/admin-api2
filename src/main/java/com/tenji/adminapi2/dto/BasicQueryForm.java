package com.tenji.adminapi2.dto;

import java.io.Serializable;

public class BasicQueryForm implements Serializable {
    private static  final long serialVersionUID =6217608607869386235L;

    private Integer pageNum =1;

    private Integer pageSize = 10;

    public Integer getPageNum(){ return pageNum;}

    public  void setPageNum(Integer pageNum){
        if(pageNum == null){
            pageNum =1;
        }
        this.pageNum=pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize==null){
            pageSize=10;
        }
        this.pageSize = pageSize;
    }


}
