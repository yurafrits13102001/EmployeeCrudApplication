package com.example.EmployeeCrudApplication.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class RestException extends RuntimeException{

    @Getter
    protected final HttpStatus httpStatus;

    public RestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public RestException(String message, HttpStatus httpStatus, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }
}