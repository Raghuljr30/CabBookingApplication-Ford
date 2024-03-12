package com.cabBooker.cabBookingApplication.booking;

public class CustomerNotFoundException extends Exception{

    public CustomerNotFoundException(String message)
    {
        super(message);
    }
}
