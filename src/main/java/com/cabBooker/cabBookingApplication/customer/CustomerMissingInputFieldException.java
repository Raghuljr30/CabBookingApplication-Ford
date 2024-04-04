package com.cabBooker.cabBookingApplication.customer;

public class CustomerMissingInputFieldException extends Exception {
    public CustomerMissingInputFieldException(String message) {
        super(message);
    }
}
