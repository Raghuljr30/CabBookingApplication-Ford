package com.cabBooker.cabBookingApplication.Authentication;

public class CabAgencyNotAuthenticatedException extends  Exception {

    public CabAgencyNotAuthenticatedException(String message)
    {
        super(message);
    }
}
