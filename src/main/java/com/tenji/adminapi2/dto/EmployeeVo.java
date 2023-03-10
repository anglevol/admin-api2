package com.tenji.adminapi2.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeVo {

    private Long id;
    private String employeeId;

    private String name;

    private String departmentCode;

    private String departmentName;

    private Integer genderCode;

    private String  gender;

    private String employDate;

    private Integer remainingDays;

    private String comment;




}

