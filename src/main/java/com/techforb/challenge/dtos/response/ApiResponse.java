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

//    Caso en el que hay una respuesta exitosa con un objeto de respuesta
    public ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }
//    Caso en el que hay una respuesta exitosa sin un objeto de respuesta
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
//    Caso en el que hay una respuesta fallida
    public ApiResponse(boolean success, ApiErrorResponse error, String message) {
        this.success = success;
        this.error = error;
        this.message = message;
    }


}