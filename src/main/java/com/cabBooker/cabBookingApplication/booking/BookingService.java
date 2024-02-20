package com.cabBooker.cabBookingApplication.booking;

import com.cabBooker.cabBookingApplication.customer.Customer;

public interface BookingService {
    public Customer bookCab(Customer customer, String pickUpLocation, String dropLocation);

}
