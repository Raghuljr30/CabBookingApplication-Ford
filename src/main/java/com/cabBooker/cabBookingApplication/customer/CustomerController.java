package com.cabBooker.cabBookingApplication.customer;

import com.cabBooker.cabBookingApplication.booking.BookingService;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgency;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgencySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CabAgencySerivce cabAgencySerivce;

    @Autowired
    private BookingService bookingService;


    /** Customer Registration*/
    @PostMapping("/customer")
    public Customer registerCustomer(@RequestBody Customer customer)
    {
        return  this.customerService.registerNewCustomer(customer);
    }

    @DeleteMapping("/customer/{id}")
    public CustomerDto deleteCustomerById(@PathVariable("id") Integer customerId)
    {
        return this.customerService.deleteCustomerById(customerId);

    }





}
