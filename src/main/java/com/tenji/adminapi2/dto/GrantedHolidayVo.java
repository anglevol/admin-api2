package com.tenji.adminapi2.dto;

import lombok.Data;

import java.util.Date;

@Data
public class GrantedHolidayVo {

    //ID
    private long id;
    //社員ID
    private Integer employeeId;
    //社員氏名
    private String employeeName;
    //部署
    private String departmentName;
    //入社日
    private Date employDate;
    //付与時社歴
    private float grantedServiceYears;
    //付与年
    private Date grantedDate;
    //基本失効日
    private Date expiryDate;
    //繰越失効日
    private Date carryoverExpiryDate;
    //状態
    private String status;
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
