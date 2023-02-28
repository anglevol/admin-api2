package com.tenji.adminapi2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class GrantedHolidayVo {
    //ID
    private long id;
    //社員コード
    private String employeeCode;
    //社員氏名
    private String employeeName;
    //部署
    private String departmentName;
    //付与時社歴
    private float grantedServiceYears;
    //付与年
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
    private Date grantedDate;
    //基本失効日
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
    private Date expiryDate;
    //繰越失効日
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    private Date createTime;
    //更新時間
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    private Date updateTime;
}
