package com.cabBooker.cabBookingApplication.booking;

import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabService;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgencySerivce;
import com.cabBooker.cabBookingApplication.customer.Customer;
import com.cabBooker.cabBookingApplication.customer.CustomerRepository;
import com.cabBooker.cabBookingApplication.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:4200"})
@RestController
public class BookingController {


    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CabService cabService;

//    @PatchMapping("/book/{id}/{email}/{from}/{to}")
//    public Customer bookCab(@PathVariable("id")Integer id, @PathVariable("from")String pickUpLocation,
//                            @PathVariable("to")String dropLocation)
//    {
//        return this.bookingService.bookCab(
//                this.customerService.findCustomer(id),
//                pickUpLocation,dropLocation);
//    }

    @GetMapping("/bookings")
    public List<Booking> displayAllBookings()
    {
        return this.bookingService.displayAllBookings();
    }

    @PatchMapping ("/booking/book/{customerId}/{cabId}/{paymentType}")
    public Customer bookCabByCabId(@PathVariable("customerId") Integer customerId,
                                   @PathVariable("cabId") Integer cabId,
                                   @PathVariable("paymentType") String paymentType) throws CabNotFoundException, CustomerNotFoundException, CabNotAvailableException, CustomerCurrentBookingExistException {


            return this.bookingService.bookCabByCabId(customerId,cabId,paymentType);

    }


    @DeleteMapping("/booking/{customerId}/{bookingId}")
    public Booking deleteBookingById(@PathVariable("bookingId")Integer bookingId,
                                     @PathVariable("customerId")Integer customerId) throws BookingNotFoundException, CustomerNotFoundException {

            return this.bookingService.deleteBookingById(bookingId,customerId);

    }


    //yes
    @GetMapping("/booking/available-cabs")
    public List<Cab> displayAllAvailableCabs()
    {
        return this.bookingService.displayAllAvailableCabs();
    }


    //yes
    @GetMapping("/booking/bylocation/{fromLocation}/{toLocation}")
    public List<Cab> searchAvailableCabsByLocation(@PathVariable("fromLocation")String fromLocation,
                                                   @PathVariable("toLocation")String toLocation)
    {
        return this.bookingService.searchAvailableCabsByLocation(fromLocation,toLocation);
    }


    //yes
    @GetMapping("/booking/current-bookings/{cabAgencyId}")
    public List<Booking> displayAllCurrentBookingsOfCabAgency(@PathVariable("cabAgencyId")Integer
                                                              cabAgencyId)
    {
        return this.bookingService.displayAllCurrentBookingsOfCabAgency(cabAgencyId);

    }

    //yes
    @GetMapping("/booking/all-bookings/{cabAgencyId}")
    public List<Booking> displayAllBookingsOfCabAgency(@PathVariable("cabAgencyId")Integer cabAgencyId)
    {
        return this.bookingService.displayAllBookingsOfCabAgency(cabAgencyId);
    }

    //yes
    @PatchMapping("/booking/completed/{customerId}/{bookingId}")
    public Customer bookingOver(@PathVariable("customerId") Integer customerId,
                                @PathVariable("bookingId") Integer bookingId) throws BookingNotFoundException, CustomerNotFoundException {

        return this.bookingService.bookingOver(customerId,bookingId);

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
