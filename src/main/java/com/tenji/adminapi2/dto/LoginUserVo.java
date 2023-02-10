package com.tenji.adminapi2.dto;

import lombok.Data;

@Data
public class LoginUserVo {

    private Long userId;

    private String userName;

    private String token;
}
