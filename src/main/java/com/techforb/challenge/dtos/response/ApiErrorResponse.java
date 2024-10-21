package com.techforb.challenge.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ApiErrorResponse {
    private int status;
    private String message;
}