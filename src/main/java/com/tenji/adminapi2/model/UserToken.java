package com.tenji.adminapi2.model;

import lombok.Data;

@Data
public class UserToken {
    private Long userId;

    private String token;
}
