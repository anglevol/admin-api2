package com.tenji.adminapi2.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeDto {
    private String employeeid;

    private Integer userId;

    private String name;

    private String departmentCode;

    private Integer gender;

    private Date employdate;

    private Integer remainingDays;

    private String comment;
}