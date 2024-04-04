package com.cabBooker.cabBookingApplication.payment;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.customer.Customer;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    public Payment makePayment(Customer customer, Booking booking, Cab cab, String payment);
}
