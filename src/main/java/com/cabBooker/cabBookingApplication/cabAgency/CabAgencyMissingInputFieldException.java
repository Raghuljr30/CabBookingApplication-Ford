package com.cabBooker.cabBookingApplication.cabAgency;

public class CabAgencyMissingInputFieldException extends Exception{

    public CabAgencyMissingInputFieldException(String message)
    {
        super(message);
    }
}
