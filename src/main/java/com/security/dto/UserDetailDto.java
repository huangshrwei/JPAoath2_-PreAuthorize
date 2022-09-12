package com.security.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDetailDto {

    private String userName;

    private String firstName;

    private String lastName;

    private String address;

    private String email;

    private String sex;

    private String edu;

    private Date birthday;

    private String professional;
    
    private byte[] photo;
}
