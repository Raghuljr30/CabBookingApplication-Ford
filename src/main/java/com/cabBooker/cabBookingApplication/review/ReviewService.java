package com.cabBooker.cabBookingApplication.review;

import com.cabBooker.cabBookingApplication.customer.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface ReviewService {
    Review postReview(Review review) throws ReviewException;

    List<Review> getReview(List<Review> reviewList) throws ReviewException;

    Review addReview(Integer customerId, Integer bookingId, String customerReview)throws ReviewException;



    Review getReviewById(Integer reviewId) throws ReviewException;


    //Review reviewByCustomer(Integer customerId, Integer bookingId, Integer cabId);
}
