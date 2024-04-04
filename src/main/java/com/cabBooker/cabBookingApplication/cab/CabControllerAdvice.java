package com.cabBooker.cabBookingApplication.cab;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CabControllerAdvice {

    @ExceptionHandler(CabException.class)
    public ResponseEntity<String> handleNullFieldException(CabException e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);


    }
}
