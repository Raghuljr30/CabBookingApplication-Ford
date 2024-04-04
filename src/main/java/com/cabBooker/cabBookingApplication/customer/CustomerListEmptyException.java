package com.cabBooker.cabBookingApplication.customer;

public class CustomerListEmptyException extends Exception{
    public CustomerListEmptyException(String message) {
        super(message);
    }
}
