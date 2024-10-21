package com.techforb.challenge.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String message;
    private ApiErrorResponse error;

    public ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }
    public ApiResponse(boolean success, ApiErrorResponse error, String message) {
        this.success = success;
        this.error = error;
        this.message = message;
    }
    public ApiResponse(boolean success, ApiErrorResponse error) {
        this.success = success;
        this.error = error;
    }

}