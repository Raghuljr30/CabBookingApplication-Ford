package com.cabBooker.cabBookingApplication.booking;

import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cabAgency.CabNotFoundInCabAgencyException;
import com.cabBooker.cabBookingApplication.customer.Customer;

import java.util.List;

public interface BookingService {
//    public Customer bookCab(Customer customer, String pickUpLocation, String dropLocation);
    public List<Booking> displayAllBookings();
//    public Customer mapBookingAndCustomer(Integer customerId,String customerName);
    public Customer bookCabByCabId(Integer customerId,Integer cabId,String paymentType) throws CustomerNotFoundException, CustomerCurrentBookingExistException, CabNotFoundException,CabNotAvailableException;
    public  Customer bookingOver(Integer customerId,Integer bookingId) throws CustomerNotFoundException, BookingNotFoundException;
    public Booking deleteBookingById(Integer bookingId,Integer customerId) throws CustomerNotFoundException,BookingNotFoundException;
//    public Customer bookCab(Customer customer);

    public List<Cab> displayAllAvailableCabs();
    public List<Cab> searchAvailableCabsByLocation(String fromLocation,String toLocation);
}


// book
// customer -> booking create
