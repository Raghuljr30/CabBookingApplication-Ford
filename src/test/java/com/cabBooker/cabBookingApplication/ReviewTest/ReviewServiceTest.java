package com.cabBooker.cabBookingApplication.ReviewTest;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.booking.BookingRepository;
import com.cabBooker.cabBookingApplication.booking.BookingService;
import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabRepository;
import com.cabBooker.cabBookingApplication.cab.CabService;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgency;
import com.cabBooker.cabBookingApplication.customer.Customer;
import com.cabBooker.cabBookingApplication.customer.CustomerException;
import com.cabBooker.cabBookingApplication.customer.CustomerRepository;
import com.cabBooker.cabBookingApplication.customer.CustomerService;
import com.cabBooker.cabBookingApplication.driver.Driver;
import com.cabBooker.cabBookingApplication.driver.DriverRepository;
import com.cabBooker.cabBookingApplication.driver.DriverService;
import com.cabBooker.cabBookingApplication.review.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ReviewServiceTest {
   @Autowired
    private ReviewService reviewService;
   @Autowired
    private ReviewRepository reviewRepository;
   @Autowired
    private DriverService driverService;
   @Autowired
   private DriverRepository driverRepository;
   @Autowired
   private CabService cabService;
   @Autowired
   private CabRepository cabRepository;
   @Autowired
   private BookingService bookingService;
   @Autowired
   private BookingRepository bookingRepository;
   @Autowired
   private CustomerService customerService;
   @Autowired
   private CustomerRepository customerRepository;

@BeforeEach
public void createReview()
{

    Review review=Review.builder().review("good").build();
    Driver driver=Driver.builder().review(Arrays.asList(review)).build();
    Cab cab=Cab.builder().driver(driver).build();
    Booking booking=Booking.builder().bookingId(1).cab(cab).build();
    Customer customer=Customer.builder().id(1).bookings(Arrays.asList(booking)).build();
    reviewRepository.save(review);
    driverRepository.save(driver);
    cabRepository.save(cab);
    bookingRepository.save(booking);
    customerRepository.save(customer);
}
@Test
public void testPostReviewWithNull(){
    try{
        reviewService.postReview(Review.builder().reviewId(null).reviewedBy(1).review(null).bookingId(1).build());
    } catch (ReviewException e) {
       Assertions.assertEquals("input values can not be null",e.getMessage());
    }
}
    @Test
    public void testPostReviewWithThreeNull(){
        try{
            reviewService.postReview(Review.builder().reviewId(null).reviewedBy(1).review(null).bookingId(null).build());
        } catch (ReviewException e) {
            Assertions.assertEquals("input values can not be null",e.getMessage());
        }
    }
@Test
    public void testPostReviewWithReviewNull(){
    try {
        reviewService.postReview(Review.builder().reviewedBy(4).reviewId(1).review(null).bookingId(1).build());
    } catch (ReviewException e) {
        Assertions.assertEquals("input values can not be null",e.getMessage());
    }
}
    @Test
    public void testPostReviewWithAllNull(){
        try{
            reviewService.postReview(Review.builder().reviewId(null).reviewedBy(null).review(null).bookingId(null).build());
        } catch (ReviewException e) {
            Assertions.assertEquals("input values can not be null",e.getMessage());
        }
    }
@Test
    public void testWithNullPostReviews(){
    try {
        reviewService.postReview(Review.builder().reviewedBy(null).bookingId(null).reviewId(null).review(null).build());
    } catch (ReviewException e) {
        Assertions.assertEquals("input values can not be null",e.getMessage());

    }

}
@Test
    public void testGetReview(){
    try {
       List<Review> reviews= reviewService.getReview(new ArrayList<>());
       Assertions.assertNotNull(reviews);
       Assertions.assertNotEquals(0,reviews);
    } catch (ReviewException e) {
        Assertions.assertEquals("no reviews is the list",e.getMessage());
    }

}

    @Test
    public void testByBookingIdAndGetReview(){
        try {
            reviewService.postReview(Review.builder().bookingId(1).review("good").reviewedBy(1).reviewId(10).build());
            Review review=reviewRepository.findById(1).get();
            Assertions.assertEquals(1,review.getBookingId());
        } catch (ReviewException e) {
            Assertions.assertEquals("BookingID does not exist",e.getMessage());
        }

    }
    @Test
    public void testByCustomerIdAndGetReview(){
        try {
            reviewService.postReview(Review.builder().bookingId(1).review("good").reviewedBy(1).reviewId(10).build());
            Review review=reviewRepository.findById(1).get();
            Assertions.assertEquals(1,review.getReviewedBy());
        } catch (ReviewException e) {
            Assertions.assertEquals("CustomerId does not exist",e.getMessage());
        }

    }
    @Test
    public void testByReviewAndGetReview(){
        try {
            reviewService.postReview(Review.builder().bookingId(1).review("good").reviewedBy(1).reviewId(10).build());
            Review review=reviewRepository.findById(1).get();
            Assertions.assertEquals("good",review.getReview());
        } catch (ReviewException e) {
            Assertions.assertEquals("CustomerId does not exist",e.getMessage());
        }

    }

    @Test
    public void testGetReviewById(){
    try{
        reviewService.getReviewById(1);
    } catch (ReviewException e) {
        Assertions.assertEquals("input can not be null",e.getMessage());
    }
    }
    @Test
    public void testGetReviewWithReview(){
        try {

            List<Review> reviews= reviewService.getReview(new ArrayList<>());
            Assertions.assertNotNull(reviews);
            Assertions.assertNotEquals(0,reviews);
        } catch (ReviewException e) {
            Assertions.assertEquals("no reviews is the list",e.getMessage());
        }

    }

    @Test
    public void testPostReview(){
    try{
        reviewService.postReview(Review.builder().reviewId(1).reviewedBy(1).review("good").bookingId(1).build());
        Review review=reviewRepository.findById(1).get();
       Assertions.assertNotEquals(1,reviewRepository.findById(review.getReviewedBy()));
    } catch (ReviewException e) {
        Assertions.assertEquals("Customer id mismatches",e.getMessage());
    }
    }



}





