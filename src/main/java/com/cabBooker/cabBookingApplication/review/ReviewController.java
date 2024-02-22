package com.cabBooker.cabBookingApplication.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {
  @Autowired
  private ReviewService reviewService;
    @PostMapping("/reviews")
    public Review postReview(@RequestBody Review review){

      return this.reviewService.postReview(review);
    }
//    @PatchMapping("/reviewByCustomer/{customerId}/{bookingId}/{cabId}")
//    public Review reviewByCustomer(@PathVariable("customerId")Integer customerId,@PathVariable("bookingId") Integer bookingId,@PathVariable("cabId")Integer cabId){
//        return this.reviewService.reviewByCustomer(customerId,bookingId,cabId);
//    }
}
