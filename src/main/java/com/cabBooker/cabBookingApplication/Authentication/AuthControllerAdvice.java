package com.cabBooker.cabBookingApplication.Authentication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthControllerAdvice {


    @ExceptionHandler(CabAgencyNotAuthenticatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEmployeeException(Exception e){
        return e.getMessage();
    }
}
