package com.almuhsin.saham.dto;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String username;
    private String password;
    private String role;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String parentName;
    private Integer createdBy; // ID admin atau sistem yang buat
}
