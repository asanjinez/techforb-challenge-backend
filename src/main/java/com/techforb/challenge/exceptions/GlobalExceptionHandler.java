package com.techforb.challenge.exceptions;

import com.techforb.challenge.dtos.response.ApiErrorResponse;
import com.techforb.challenge.dtos.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleResourceAlreadyExists(ResourceAlreadyExistsException ex) {
        log.error("Recurso existente: {}", ex.getMessage());
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());
        ApiResponse<String> errorResponse = new ApiResponse<>(false, apiErrorResponse, "Recurso existente");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<String>> handleBadCredentials(BadCredentialsException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.UNAUTHORIZED.value(),ex.getMessage());
        ApiResponse<String> errorResponse = new ApiResponse<>(false, apiErrorResponse, "Credenciales incorrectas.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {
        log.error("Se produjo un error inesperado: {}", ex.getCause(), ex);
        ApiErrorResponse apiError = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error en el servidor");
        ApiResponse<Void> errorResponse = new ApiResponse<>(false, apiError);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
