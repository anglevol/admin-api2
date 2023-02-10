package com.tenji.adminapi2.model;

import lombok.Data;

@Data
public class User {
    private Long userId;

    private String userName;

    private String passWord;

    private String token;
}
