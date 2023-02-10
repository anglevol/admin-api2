package com.tenji.adminapi2.model;

import lombok.Data;

@Data
public class GrantedHoliday {
    //ユーザーID
    private long userId;
    //付与時社歴
    private float grantedServiceYears;
    //付与年
    private String grantedDate;
    //基本失効日
    private String expiryDate;
    //繰越失効日
    private String carryoverExpiryDate;
    //付与日数
    private int grantedDays;
    //消化日数
    private int usedDays;
    //未消化日数
    private int unusedDays;
    //残日数
    private int remainingDays;
}
