package com.tenji.adminapi2.model;

import lombok.Data;

import java.util.Date;
@Data
public class Employee {
    private Integer id;

    private String employeeid;

    private Integer userId;

    private String name;

    private Integer departmentId;

    private Integer gender;

    private Date employdate;

    private Integer remainingDays;

    private String comment;

    private Date createdate;

    private Date updatedate;

}