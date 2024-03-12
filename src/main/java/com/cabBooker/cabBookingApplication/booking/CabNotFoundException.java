package com.cabBooker.cabBookingApplication.booking;

public class CabNotFoundException extends Exception{

    public CabNotFoundException(String message)
    {
        super(message);
    }
}
