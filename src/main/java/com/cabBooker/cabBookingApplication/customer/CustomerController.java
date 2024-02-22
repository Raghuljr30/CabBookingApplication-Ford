package com.cabBooker.cabBookingApplication.customer;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.booking.BookingService;
import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgency;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgencySerivce;
import com.cabBooker.cabBookingApplication.review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ClientInfoStatus;
import java.time.LocalDate;
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

   @GetMapping("/bookings")
    public List<Booking> allBookings(@RequestBody List<Booking> customerBookings){
        return this.customerService.allBookings(customerBookings);

   }

    @GetMapping("/customer/{id}/{password}")
    public Customer viewCustomer(@PathVariable("id") Integer customerId,@PathVariable("password") String customerPassword){
        return this.customerService.viewCustomer(customerId,customerPassword);
    }
    @PatchMapping("/updateCustomerMobile/{id}/{mobilenumber}")
    public Customer updateCustomerMobile(@PathVariable("id") Integer customerId,@PathVariable("mobilenumber") Long customerMobileNumber){

        return  this.customerService.updateCustomerMobile(customerId,customerMobileNumber);
    }
//    @PatchMapping("/updateCustomerEmail/{id}/{email}")
//    public Customer updateCustomerEmail(@PathVariable("id") Integer customerId,@PathVariable("email") String customerEmail){
//
//        return  this.customerService.updateCustomerEmail(customerId,customerEmail);
//    }
    @PatchMapping("/updateCustomerPassword/{id}/{password}")
    public Customer updateCustomerPassword(@PathVariable("id") Integer customerId,@PathVariable("password")String customerPassword){
        return  this.customerService.updateCustomerPassword(customerId,customerPassword);
    }

    @DeleteMapping("/{id}")
    public Customer deleteCustomerByID(@PathVariable("id") Integer customerId){
        return this.customerService.deleteCustomerById(customerId);
    }

    @GetMapping("/filterByFair/{fair}")
    public  List<Booking> filterBookingByFair(@PathVariable("fair") Double cabFair){
        return this.customerService.filterBookingByFair(cabFair);
    }

//    @GetMapping("/cabFair/{agencyId}/{cabId}")
//    public Double cabFairById(@PathVariable("AgencyId") Integer cabAgencyId,@PathVariable("cabId") Integer cabId){
//        return this.customerService.cabFairById(cabAgencyId,cabId);
//    }

    @PutMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer){
        return this.customerService.updateCustomer(customer);
    }








//    @GetMapping("/{cabAgency}/{pickUpLocation}/{dropLocation}/{bookingDate}")
//    public List<Cab> availableCabs(@PathVariable("cabAgency") String cabAgency, @PathVariable("pickUpLoaction")String pickUpLocation, @PathVariable("dropLocation") String dropLocation, @PathVariable("bookingDate")LocalDate bookingDate){
//        return this.customerService.availableCabs(cabAgency,pickUpLocation,dropLocation,bookingDate);
//    }







}
