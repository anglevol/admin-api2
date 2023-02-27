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
    //基本失効日
    private Date expiryDate;
    //繰越失効日
    private Date carryoverExpiryDate;
    //状態コード
    private String statusCode;
    //付与日数
    private int grantedDays;
    //消化日数
    private int usedDays;
    //未消化日数
    private int unusedDays;
    //残日数
    private int remainingDays;
    //作成時間
    private Date createTime;
    //更新時間
    private Date updateTime;
}
