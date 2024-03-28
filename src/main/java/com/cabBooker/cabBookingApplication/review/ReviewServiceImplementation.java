package com.cabBooker.cabBookingApplication.review;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.customer.Customer;
import com.cabBooker.cabBookingApplication.customer.CustomerRepository;
import com.cabBooker.cabBookingApplication.driver.Driver;
import com.cabBooker.cabBookingApplication.driver.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImplementation implements ReviewService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private  ReviewRepository reviewRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Override
    public Review addReview(Integer customerId, Integer bookingId, String customerReview)throws ReviewException {


            Optional<Customer> customer=this.customerRepository.findById(customerId);
            Customer customerExist=customer.get();
            List<Booking> bookingByCustomers=customerExist.getBookings();
            Review review=new Review();
            review.setReviewedBy(customerId);
            review.setReview(customerReview);
            review.setBookingId(bookingId);
            if(review.getReviewedBy()!=customerId||review.getBookingId()!=bookingId){
                throw new ReviewException("input field mis match enter a correct input");
            }

            this.reviewRepository.save(review);

            List<Review> listOfReviews= findByReview(customerId,bookingId);
            System.out.println(listOfReviews+"-----------------------------------------------------");

            customerExist.getBookings().stream().filter(booking -> booking.getBookingId()==bookingId).
                    forEach(booking -> booking.getCab().getDriver().setReviews(listOfReviews));
            customerRepository.save(customerExist);

            return review;

        }
        private List<Review> findByReview(Integer reviewedBy, Integer bookingId)  {

            return this.reviewRepository.findByReviewedByAndBookingId(reviewedBy,bookingId);
        }


    @Override
    public List<Review> displayReviews() {
        return this.reviewRepository.findAll();
    }

    public List<Review> findListOfReviews(Integer reviewedBy,Integer bookingId)
    {
       return this.reviewRepository. findByReviewedByAndBookingId(reviewedBy,bookingId);
    }
}
