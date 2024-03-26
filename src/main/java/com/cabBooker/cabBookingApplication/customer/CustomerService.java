package com.cabBooker.cabBookingApplication.customer;

import com.cabBooker.cabBookingApplication.booking.Booking;

import java.util.List;

public interface CustomerService {

    public Customer registerNewCustomer(Customer customer);

    public Boolean loginCustomer(Integer id,String email,String password);
    public List<Customer> displayAllCustomers();
    public Customer findCustomer(Integer customerId);
//    public Customer bookCab(Integer customerId,String pickUpLocation,String dropLocation);
    public CustomerDto deleteCustomerById(Integer customerId);
    public Booking displayCurrentBooking(Integer customerId);


}
