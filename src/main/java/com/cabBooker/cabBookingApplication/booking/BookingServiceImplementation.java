package com.cabBooker.cabBookingApplication.booking;

import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabRepository;
import com.cabBooker.cabBookingApplication.customer.Customer;
import com.cabBooker.cabBookingApplication.customer.CustomerRepository;
import com.cabBooker.cabBookingApplication.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImplementation implements  BookingService{
    @Autowired
    private CabRepository cabRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public Customer bookCab(Customer customer, String pickUpLocation, String dropLocation) {
      Cab cab= this.cabRepository.findByPickUpPointAndDropPoint(pickUpLocation,dropLocation);
      Booking booking=new Booking();
      booking.setCab(cab);
      booking.setCabNumber(cab.getVehicleNumber());
      booking.setCustomerName(customer.getName());
      booking.setPickUpLocation(pickUpLocation);
      booking.setDropLocation(dropLocation);
      this.bookingRepository.save(booking);
      List<Booking>bookings=new ArrayList<>();
      bookings.add(booking);
      customer.setBookings(bookings);

      return  this.customerRepository.save(customer);



    }
}
