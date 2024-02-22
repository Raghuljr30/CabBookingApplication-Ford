package com.cabBooker.cabBookingApplication.booking;

import com.cabBooker.cabBookingApplication.cabAgency.CabAgencySerivce;
import com.cabBooker.cabBookingApplication.customer.Customer;
import com.cabBooker.cabBookingApplication.customer.CustomerRepository;
import com.cabBooker.cabBookingApplication.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingController {
    
    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookingService bookingService;

    @PatchMapping("/book/{id}/{email}/{from}/{to}")
    public Customer bookCab(@PathVariable("id")Integer id, @PathVariable("from")String pickUpLocation,
                            @PathVariable("to")String dropLocation)
    {
        return this.bookingService.bookCab(
                this.customerService.findCustomer(id),
                pickUpLocation,dropLocation);
    }

    @GetMapping("/bookings")
    public List<Booking> displayAllBookings()
    {
        return this.bookingService.displayAllBookings();
    }

    @PatchMapping ("/book/{customerId}/{cabId}/{paymentType}")
    public Customer bookCabByCabId(@PathVariable("customerId") Integer customerId,
                                   @PathVariable("cabId") Integer cabId,
                                   @PathVariable("paymentType") String paymentType)
    {
        return this.bookingService.bookCabByCabId(customerId,cabId,paymentType);
    }

    @PatchMapping("/bookingOver/{customerId}")
    public Customer bookingOver(@PathVariable("customerId") Integer customerId)
    {
        return this.bookingService.bookingOver(customerId);
    }
    // cab customer ,
//    @PatchMapping("/patch-customer-booking/{customerId}/{customerName}")
//    public Customer mapBookingAndCustomer(@PathVariable("customerId") Integer customerId,
//                                          @PathVariable("customerName")String customerName)
//    {
////        return  this.bookingService.bookCab(this.customerService.findCustomer(customerId),customerName);
//          return null;
//    }
//
//    @PatchMapping("/bookcab/{customerId}")
//    public Customer bookCab(@PathVariable("customerId")Integer customerId)
//    {
//        this.bookingService.bookCab(this.customerService.findCustomer(customerId))
//    }

}
