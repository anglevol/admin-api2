package com.tenji.adminapi2.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeVo {

    private String employeeId;

    private String name;

    private String departmentCode;

    private String departmentName;

    private String  gender;

    private String employDate;

    private Integer remainingDays;




}

