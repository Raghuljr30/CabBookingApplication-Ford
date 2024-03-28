package com.cabBooker.cabBookingApplication.review;

import com.cabBooker.cabBookingApplication.customer.Customer;

import java.util.List;

public interface ReviewService {
    public Review addReview(Integer customerId,Integer bookingId,String review) throws ReviewException;
    public List<Review> displayReviews();

}
