package com.techforb.challenge.exceptions;

public class JwtAuthException extends RuntimeException {
    public JwtAuthException(String message) {
        super(message);
    }
}
