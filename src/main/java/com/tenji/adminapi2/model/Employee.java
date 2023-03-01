package com.tenji.adminapi2.model;

import lombok.Data;

import java.util.Date;
@Data
public class Employee {
    private Long id;

    private String employeeid;

    private Integer userId;

    private String name;

    private String departmentCode;

    private Integer gender;

    private Date employdate;

    private Integer remainingDays;

    private String comment;

    private Date createdate;

    private Date updatedate;

    private Integer deleted;
}
