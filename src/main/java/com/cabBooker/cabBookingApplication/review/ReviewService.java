package com.cabBooker.cabBookingApplication.review;

import com.cabBooker.cabBookingApplication.customer.Customer;

import java.util.List;

public interface ReviewService {
    public Customer addReview(Integer customerId,Integer bookingId,String review);
    public List<Review> displayReviews();

}
