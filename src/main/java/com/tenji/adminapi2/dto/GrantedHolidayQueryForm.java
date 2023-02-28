package com.tenji.adminapi2.dto;

import lombok.Data;

/**
 * 付与一覧検索フォームの値を取得するクラス
 */
@Data
public class GrantedHolidayQueryForm extends BasicQueryForm{
    //社員ID
    private long employeeId;

}
