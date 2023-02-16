package com.tenji.adminapi2.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserSearchForm extends BasicQueryForm implements Serializable {
    private static  final long serialVersionUID =6217608607869386235L;

    private String name;


}
