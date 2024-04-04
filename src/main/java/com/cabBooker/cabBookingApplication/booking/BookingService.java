package com.cabBooker.cabBookingApplication.booking;

import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.customer.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface BookingService {
    public Customer bookCab(Customer customer,String customerEmail, String pickUpLocation, String dropLocation);
    public Customer bookCabByCabId(Integer customerId,Integer cabId,String paymentType);

    List<Booking> bookingByCustomer(Integer customerId);



}
