package com.techforb.challenge.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techforb.challenge.dtos.response.ApiErrorResponse;
import com.techforb.challenge.dtos.response.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
    public class JwtAuthInterceptor implements AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.UNAUTHORIZED.value(), authException.getMessage());
            ApiResponse<String> apiResponse = new ApiResponse<>(false, apiErrorResponse, "Error en la autenticación");
            response.getWriter().write(new ObjectMapper().writeValueAsString(apiResponse));
        }
    }
