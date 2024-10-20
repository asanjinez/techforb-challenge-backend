package com.techforb.challenge.dtos;

import lombok.Data;

@Data
public class AuthResponsoDto {
    private String token;
    private String tokenType = "Bearer: ";

    public AuthResponsoDto(String token) {
        this.token = token;
    }
}
