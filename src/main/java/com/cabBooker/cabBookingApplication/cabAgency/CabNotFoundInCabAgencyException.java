package com.cabBooker.cabBookingApplication.cabAgency;

import com.cabBooker.cabBookingApplication.cab.CabNotFoundException;

public class CabNotFoundInCabAgencyException extends Exception
{
    public CabNotFoundInCabAgencyException(String message)
    {
        super(message);
    }
}

