package com.cabBooker.cabBookingApplication.review;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.booking.BookingRepository;
import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabRepository;
import com.cabBooker.cabBookingApplication.customer.Customer;
import com.cabBooker.cabBookingApplication.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImplementation implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CabRepository cabRepository;


    @Override
    public Review postReview(Review review) {
        return this.reviewRepository.save(review);
    }

//    @Override
//    public Review reviewByCustomer(Integer customerId, Integer bookingId, Integer cabId) {
//        Customer customer=this.customerRepository.findById(customerId).get();
//
//       Booking Booking=this.bookingRepository.findById(bookingId).get();
//       this.customerRepository.findById(customerId).get().getBookings().
//               stream().filter((booking)->booking.getBookingId()==bookingId);
//       Booking.getCab().getDriver().setReviews();
//
//        return ;
//    }
}
