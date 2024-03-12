package com.cabBooker.cabBookingApplication.BookingTest;

import com.cabBooker.cabBookingApplication.booking.*;
import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabRepository;
import com.cabBooker.cabBookingApplication.customer.Customer;
import com.cabBooker.cabBookingApplication.customer.CustomerRepository;
import com.cabBooker.cabBookingApplication.customer.CustomerService;
import com.cabBooker.cabBookingApplication.driver.Driver;
import com.cabBooker.cabBookingApplication.driver.DriverRepository;
import com.cabBooker.cabBookingApplication.review.Review;
import com.cabBooker.cabBookingApplication.review.ReviewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootTest
public class BookingServiceTest {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CabRepository cabRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void createBooking()
    {


        Review review=Review.builder().reviewId(1).build();
        Driver driver= Driver.builder().driverId(1).reviews( Arrays.asList(review)).build();
        Cab cab= Cab.builder().cabId(1).availability(true).driver(driver). build();
        Cab cab2= Cab.builder().cabId(2).availability(false).build();
//        Booking booking=Booking.builder().bookingId(1).cab(cab).build();
        Booking booking= Booking.builder().bookingId(1).cab(cab).build();

        Customer customer=Customer.builder().name("ragh")
                .id(1).email("raghu@gmail.com").currentBookingExist(false).bookings(Arrays.asList(booking)).build();
        this.reviewRepository.save(review);
        this.driverRepository.save(driver);
        this.cabRepository.save(cab);
        this.cabRepository.save(cab2);
        this.bookingRepository.save(booking);
        this.customerRepository.save(customer);





    }

    @Test
    public void displayBookingTest()
    {
        Booking booking=Booking.builder().bookingId(1).build();
        List<Booking>bookings=new ArrayList<>();
        bookings.add(booking);
        this.bookingRepository.save(booking);

        Assertions.assertNotEquals(0,this.bookingService.displayAllBookings().size());
    }

    @Test
    public void testBookCabByVabId()
    {
        try {
            Assertions.assertNotNull(this.bookingService.bookCabByCabId(1,1,"cash"));
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CustomerCurrentBookingExistException e) {
            throw new RuntimeException(e);
        } catch (CabNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CabNotAvailableException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testBookingOver()
    {
        try {
            Assertions.assertNotNull(bookingService.bookingOver(1,1));
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        } catch (BookingNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testBookingOver_check_if_cabAvailablity_updated_toTrue()
    {
        Assertions.assertEquals(false,cabRepository.findById(2).get().getAvailability());
    }




}
