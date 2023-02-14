package com.tenji.adminapi2.model;

import lombok.Data;

@Data
public class User {
    private Long id;

    private String userName;

    private String passWord;

    private String roles;

    private String introduction;

    private String avatar;

    private String name;

    //0 man 1 women
    private Integer sex;



}
