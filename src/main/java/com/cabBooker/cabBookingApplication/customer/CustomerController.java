package com.cabBooker.cabBookingApplication.customer;

import com.cabBooker.cabBookingApplication.booking.BookingService;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgency;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgencySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
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

    @PostMapping("/customer/login/{id}/{email}/{password}")
    public Boolean loginCustomer(@PathVariable("id")Integer id, @PathVariable("email")String email,
                                 @PathVariable("password")String password)
    {
        return this.customerService.loginCustomer(id,email,password);
    }

    @DeleteMapping("/customer/{id}")
    public CustomerDto deleteCustomerById(@PathVariable("id") Integer customerId)
    {
        return this.customerService.deleteCustomerById(customerId);

    }





}
