package com.cabBooker.cabBookingApplication.booking;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookingControllerAdvice {

    @ExceptionHandler(value = {BookingNotFoundException.class})
    public ResponseEntity<String> handleBookingNotFoundException(BookingNotFoundException e)
    {
        return new ResponseEntity<String>("Booking not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {CustomerNotFoundException.class})
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException e)
    {
        return new ResponseEntity<String>("Customer not found", HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {CabNotFoundException.class})
    public ResponseEntity<String> handleCabNotFoundException(CabNotFoundException e)
    {
        return new ResponseEntity<String>("Cab not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {CabNotAvailableException.class})
    public ResponseEntity<String> handleCabNotAvailableException(CabNotAvailableException e)
    {
        return new ResponseEntity<String>("Cab currently not available", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {CustomerCurrentBookingExistException.class})
    public ResponseEntity<String> handleCustomerCurrentBookingExistException(CustomerCurrentBookingExistException e)
    {
        return new ResponseEntity<String>("Booking currently exist.Cannot perform new Booking!!!", HttpStatus.BAD_REQUEST);
    }




}
