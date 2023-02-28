package com.tenji.adminapi2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class GrantedHolidayLogVo {
    //ID
    private Integer id;
    //社員ID
    private String employeeCode;
    //操作状態；付与or消化
    private String status;
    //付与or消化日数
    private Integer days;
    //作成時間
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
    private Date createTime;
}
