package com.cabBooker.cabBookingApplication.booking;

import com.cabBooker.cabBookingApplication.cabAgency.CabAgencySerivce;
import com.cabBooker.cabBookingApplication.customer.Customer;
import com.cabBooker.cabBookingApplication.customer.CustomerRepository;
import com.cabBooker.cabBookingApplication.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    
    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookingService bookingService;

    @PatchMapping("/book/{id},{email}/{from}/{to}")
    public Customer bookCab(@PathVariable("id")Integer customerId ,@PathVariable("email") String customerEmail, @PathVariable("from")String pickUpLocation,
                            @PathVariable("to")String dropLocation)
    {
        return this.bookingService.bookCab(
                this.customerService.findCustomer(customerId), pickUpLocation,dropLocation,customerEmail);
    }

}
