package com.almuhsin.saham.dto;

import lombok.Data;

@Data
public class VerifyToken {
    private String token;
    private int userId;
}
