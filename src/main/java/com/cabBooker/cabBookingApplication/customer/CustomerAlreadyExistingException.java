package com.cabBooker.cabBookingApplication.customer;

public class CustomerAlreadyExistingException extends Exception{
    public CustomerAlreadyExistingException(String message) {
        super(message);
    }
}
