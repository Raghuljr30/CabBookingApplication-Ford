package com.cabBooker.cabBookingApplication.review;

import com.cabBooker.cabBookingApplication.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
//    @PatchMapping("/addreivew/{customerId}/{bookingId}/{review}")
//    public Customer addReview(@PathVariable("customerId") Integer customerId,
//                              @PathVariable("bookingId")Integer bookingId,
//                              @PathVariable("review")String review)
//    {
//       return this.reviewService.addReview(customerId,bookingId,review);
//
//    }

    @PatchMapping("review/booking/{customerId}/{bookingId}/{customerReview}")
    public Review addReview(@PathVariable("customerId") Integer customerId,
                            @PathVariable("bookingId") Integer bookingId,
                            @PathVariable("customerReview")String customerReview)throws ReviewException
    {
        return this.reviewService.addReview(customerId,bookingId,customerReview);
    }

    @GetMapping("reviews")
    public List<Review> displayReviews()
    {
       return this.reviewService.displayReviews();
    }
}
