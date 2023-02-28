package com.tenji.adminapi2.dto;

import lombok.Data;

/**
 * 付与・消化履歴フォームの値を取得するクラス
 */
@Data
public class GrantedHolidayLogQueryForm extends BasicQueryForm{

    //社員ID
    private long employeeId;

}
