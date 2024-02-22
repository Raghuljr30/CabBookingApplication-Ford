package com.cabBooker.cabBookingApplication.cabAgency;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CabControllerAdvice {

    @ExceptionHandler(value = { CabAgencyMissingInputFieldException.class})
    public ResponseEntity<String> handleNullValueException(CabAgencyMissingInputFieldException e)
    {
        return new ResponseEntity<String>("Missing Input field in Cab Agency registration form", HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {CabAgencyNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(CabAgencyNotFoundException e)
    {
        return new ResponseEntity<String>("No such Cab Agency found",HttpStatus.BAD_REQUEST);
    }

}
