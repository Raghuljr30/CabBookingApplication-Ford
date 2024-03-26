package com.cabBooker.cabBookingApplication.cabAgency;

public class AccountAlreadyExistException extends  Exception{

    public  AccountAlreadyExistException(String message)
    {
        super(message);
    }
}
