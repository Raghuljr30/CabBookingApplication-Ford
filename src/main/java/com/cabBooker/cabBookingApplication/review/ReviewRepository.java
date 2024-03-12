package com.cabBooker.cabBookingApplication.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewRepository extends JpaRepository<Review,Integer> {
    List<Review> findByReviewedByAndBookingId(Integer reviewedBy,Integer bookingId);
}
