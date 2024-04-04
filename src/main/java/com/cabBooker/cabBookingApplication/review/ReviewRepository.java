package com.cabBooker.cabBookingApplication.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    List<Review> findByReviewedByAndBookingId(Integer reviewedBy, Integer bookingId);


}
