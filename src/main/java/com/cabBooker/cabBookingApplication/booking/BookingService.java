package com.cabBooker.cabBookingApplication.booking;

import com.cabBooker.cabBookingApplication.customer.Customer;

import java.util.List;

public interface BookingService {
    public Customer bookCab(Customer customer, String pickUpLocation, String dropLocation);
    public List<Booking> displayAllBookings();
    public Customer mapBookingAndCustomer(Integer customerId,String customerName);
    public Customer bookCabByCabId(Integer customerId,Integer cabId,String paymentType);
    public  Customer bookingOver(Integer customerId);

//    public Customer bookCab(Customer customer);



}


// book
// customer -> booking create
