package com.tenji.adminapi2.model;

import lombok.Data;

import java.util.Date;

@Data
public class GrantedHolidayLog {
    private Integer id;

    private Long employeeId;

    private String statusCode;

    private Integer days;

    private Date createTime;

}
