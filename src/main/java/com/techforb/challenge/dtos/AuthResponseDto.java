package com.techforb.challenge.dtos;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String token;
    private String tokenType = "Bearer: ";

    public AuthResponseDto(String token) {
        this.token = token;
    }
}
