package com.cabBooker.cabBookingApplication.review;

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
    public Customer addReview(Integer customerId, Integer bookingId,String review) {

        Optional<Customer> customer= this.customerRepository.findById(customerId);
        Customer customerExist= customer.get();
        Review review1=new Review();
        review1.setReviewedBy(customerId);
        review1.setReview(review);
        review1.setBookingId(bookingId);


        this.reviewRepository.save(review1);

        List<Review> reviewByCustomer= findListOfReviews(customerId,bookingId);

        customerExist.getBookings().stream().filter(booking->
                booking.getBookingId()==bookingId).forEach(
                        booking -> booking.getCab().
                                getDriver().
                                setReviews(reviewByCustomer));



//
//        Driver driver =customerExist.getBookings().stream().filter(booking->
//                booking.getBookingId()==bookingId).forEach(
//                booking -> booking.getCab().
//                        getDriver()).
//        this.driverRepository.save();

        return customerExist;




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
