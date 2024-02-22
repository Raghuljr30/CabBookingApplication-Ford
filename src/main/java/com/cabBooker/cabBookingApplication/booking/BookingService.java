package com.cabBooker.cabBookingApplication.booking;

import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.customer.Customer;

import java.util.List;

public interface BookingService {
    public Customer bookCab(Customer customer,String customerEmail, String pickUpLocation, String dropLocation);


}
