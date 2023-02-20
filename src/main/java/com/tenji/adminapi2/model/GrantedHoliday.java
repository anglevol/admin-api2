package com.tenji.adminapi2.model;

import lombok.Data;

import java.util.Date;

@Data
public class GrantedHoliday {
    private Long id;

    private Long userId;

    private Long employeeId;

    private Float grantedServiceYears;

    private Date grantedDate;

    private Date expiryDate;

    private Date carryoverExpiryDate;

    private String statusCode;

    private Integer grantedDays;

    private Integer usedDays;

    private Integer unusedDays;

    private Integer remainingDays;

    private Date createTime;

    private Date updateTime;

}
