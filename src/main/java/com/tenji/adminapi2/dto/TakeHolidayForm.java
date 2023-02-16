package com.tenji.adminapi2.dto;

import lombok.Data;

/**
 * 申請休暇フォーム対応クラス
 */
@Data
public class TakeHolidayForm {
    //付与休暇ID granted_holiday.id
    private long id;
    //休暇取る日数
    private int holiday;
}
