package com.cabBooker.cabBookingApplication.booking;

import com.cabBooker.cabBookingApplication.cabAgency.CabAgencySerivce;
import com.cabBooker.cabBookingApplication.customer.Customer;
import com.cabBooker.cabBookingApplication.customer.CustomerException;
import com.cabBooker.cabBookingApplication.customer.CustomerRepository;
import com.cabBooker.cabBookingApplication.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
public class BookingController {
    
    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookingService bookingService;

    @PatchMapping("/booking/{id},{email}/{from}/{to}")
    public Customer bookCab(@PathVariable("id")Integer customerId ,@PathVariable("email") String customerEmail, @PathVariable("from")String pickUpLocation,
                            @PathVariable("to")String dropLocation) throws CustomerException {
        return this.bookingService.bookCab(
                this.customerService.findCustomer(customerId), pickUpLocation,dropLocation,customerEmail);
    }


    @PatchMapping ("/booking/{customerId}/{cabId}/{paymentType}")
    public Customer bookCabByCabId(@PathVariable("customerId") Integer customerId,
                                   @PathVariable("cabId") Integer cabId,
                                   @PathVariable("paymentType") String paymentType){


        return this.bookingService.bookCabByCabId(customerId,cabId,paymentType);


    }
   @GetMapping("booking/customer/{customerId}")
    public List<Booking> bookingByCustomer(@PathVariable("customerId")Integer customerId){
        return this.bookingService.bookingByCustomer(customerId);
   }



}
