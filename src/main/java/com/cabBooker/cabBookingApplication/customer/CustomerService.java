package com.cabBooker.cabBookingApplication.customer;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.cab.Cab;

import java.time.LocalDate;
import java.util.List;

public interface CustomerService {

    public Customer registerNewCustomer(Customer customer);
    public List<Customer> displayAllCustomers();
    public Customer findCustomer(Integer customerId);

    Customer getCustomerById(Integer customerId);

    List<Booking> allBookings(List<Booking> customerBookings);

    Customer viewCustomer(Integer customerID, String customerPassword);



    public Customer deleteCustomerById(Integer customerId);

    Customer updateCustomerMobile(Integer customerId, Long customerMobileNumber);

    //Customer updateCustomerEmail(Integer customerId, String customerEmail);

    Customer updateCustomerPassword(Integer customerId, String customerPassword);

    List<Booking> filterBookingByFair(Double cabFair);

    Double cabFairById(Integer cabAgencyId, Integer cabId);

    Customer updateCustomer(Customer customer);


    //   List<Cab> availableCabs(String cabAgency, String pickUpLocation, String dropLocation, LocalDate bookingDate);

    //boolean isExistingCustomer(Integer customerId);
//    public Customer bookCab(Integer customerId,String pickUpLocation,String dropLocation);


}
