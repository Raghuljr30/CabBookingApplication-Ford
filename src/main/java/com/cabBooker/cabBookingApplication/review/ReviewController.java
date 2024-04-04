package com.cabBooker.cabBookingApplication.review;

import com.cabBooker.cabBookingApplication.customer.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
public class ReviewController {
  @Autowired
  private ReviewService reviewService;
/**post the review to the db **/
    @PostMapping("/review")
    public Review postReview(@RequestBody Review review) throws ReviewException{

      return this.reviewService.postReview(review);
    }
  /**retrieves the list of review **/
  @GetMapping("/reviews")
  public List<Review> getReview(   @RequestBody List<Review> reviewList)throws ReviewException {
    return this.reviewService.getReview(reviewList);
  }
 /**adding review for the booking **/
  @PatchMapping("review/booking/{customerId}/{bookingId}/{customerReview}")
  public Review addReview(@PathVariable("customerId") Integer customerId,
                            @PathVariable("bookingId") Integer bookingId,
                            @PathVariable("customerReview")String customerReview)throws ReviewException
  {
    return this.reviewService.addReview(customerId,bookingId,customerReview);
  }

 /** retrieves the review of the customer  **/
  @GetMapping("/review/bycustomer/{id}")
  public Review getReviewById(@PathVariable("id")Integer reviewId) throws ReviewException {
      return this.reviewService.getReviewById(reviewId);
  }

}
