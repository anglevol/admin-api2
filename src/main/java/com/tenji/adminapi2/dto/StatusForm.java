package com.tenji.adminapi2.dto;

import lombok.Data;

@Data
public class StatusForm {
    //付与休暇ID
    private long id;
    //変更する状態
    private int status;
}
