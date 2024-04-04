package com.cabBooker.cabBookingApplication.customer;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.cab.Cab;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service

public interface CustomerService {

    public Customer registerNewCustomer(Customer customer) throws CustomerException;
    public List<Customer> displayAllCustomers() throws CustomerListEmptyException;
    public Customer findCustomer(Integer customerId) throws CustomerException;

    Customer getCustomerById(Integer customerId);

    List<Booking> allBookings() throws CustomerListEmptyException;

    Customer viewCustomer(Integer customerID, String customerPassword) throws CustomerException;



    public Customer deleteCustomerById(Integer customerId) throws CustomerException;

    Customer updateCustomerMobile(Integer customerId, Long customerMobileNumber) throws  CustomerException;

    //Customer updateCustomerEmail(Integer customerId, String customerEmail);

    Customer updateCustomerPassword(Integer customerId, String customerPassword) throws CustomerException;





   // Customer updateCustomer(Customer customer) throws CustomerException;

    List<Customer> allCustomers();

    //Customer loginCustomer(String customerName, String customerPassword);

    Customer viewProfile(Integer customerId) throws CustomerException;



}
