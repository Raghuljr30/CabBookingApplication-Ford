package com.cabBooker.cabBookingApplication.review;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
@Builder
public class Review {

    @Id
    @GeneratedValue
    private Integer reviewId;
    private String review;
   private Integer reviewedBy;
   private Integer bookingId;

    public Review()
    {
        super();
    }

    public Review(Integer reviewId, String review, Integer reviewedBy, Integer bookingId) {
        this.reviewId = reviewId;
        this.review = review;
        this.reviewedBy = reviewedBy;
        this.bookingId = bookingId;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(Integer reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", review='" + review + '\'' +
                ", reviewedBy=" + reviewedBy +
                ", bookingId=" + bookingId +
                '}';
    }
}

