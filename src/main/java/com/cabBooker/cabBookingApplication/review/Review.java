package com.cabBooker.cabBookingApplication.review;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Integer reviewId;
    private String review;

//    private Integer reviewedBy;

    public Review()
    {
        super();
    }

    public Review(Integer reviewId, String review) {
        super();
        this.reviewId = reviewId;
        this.review = review;
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

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", review='" + review + '\'' +
                '}';
    }
}

