package com.cabBooker.cabBookingApplication.booking;

public class CustomerCurrentBookingExistException extends Exception{

    public CustomerCurrentBookingExistException(String message)
    {
        super(message);
    }
}
