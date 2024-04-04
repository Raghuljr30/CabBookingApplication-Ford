package com.cabBooker.cabBookingApplication.review;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.booking.BookingRepository;
import com.cabBooker.cabBookingApplication.booking.BookingService;
import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabRepository;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgencyRepository;
import com.cabBooker.cabBookingApplication.customer.Customer;
import com.cabBooker.cabBookingApplication.customer.CustomerRepository;
import com.cabBooker.cabBookingApplication.driver.Driver;
import com.cabBooker.cabBookingApplication.driver.DriverRepository;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImplementation implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BookingRepository bookingRepository;


    /***********************************************************
     * Description add review by the customer by entering customer id and booking and the review string
     * method postReview
     * @param review
     * @return review
     * @throws ReviewException
     */
    @Override
    public Review postReview(Review review)throws ReviewException
    {
        if(review.getReviewedBy()==null||review.getBookingId()==null||review.getReview()==null){
            throw new ReviewException("input values can not be null");
        }

        return this.reviewRepository.save(review);
    }

    /****************************************************************************
     * Description getting the list of all review to check whether the posted review added to the list
     * method getReview
     * @param reviewList
     * @return list of review
     * @throws ReviewException
     */
    @Override
    public List<Review> getReview(List<Review> reviewList)throws ReviewException {
        if(reviewList.isEmpty()){
            throw new ReviewException("no reviews is the list");
        }

        return this.reviewRepository.findAll();
    }

    /**************************************************************************
     * Description this method adds the review from the customer by accepting the customer id and booking id and the review string
     * which adds review to the bookings by booking id  it will assign to the driver repository it reflects in the review list of the driver
     * method addReview
     * @param customerId
     * @param bookingId
     * @param customerReview
     * @return review
     * @throws ReviewException
     */
    @Override
    public Review addReview(Integer customerId, Integer bookingId, String customerReview)throws ReviewException {


            Optional<Customer> customer=this.customerRepository.findById(customerId);
        if(customer.isEmpty()){
            throw new ReviewException("Customer id with "+customerId+" not found");
        }
            Customer customerExist=customer.get();
            List<Booking> bookingByCustomers=customerExist.getBookings();
            Review review=new Review();
            review.setReviewedBy(customerId);
            review.setReview(customerReview);
            review.setBookingId(bookingId);


            this.reviewRepository.save(review);

            List<Review> listOfReviews= findByReview(customerId,bookingId);
            System.out.println(listOfReviews+"-----------------------------------------------------");

            customerExist.getBookings().stream().filter(booking -> booking.getBookingId()==bookingId).
                    forEach(booking -> booking.getCab().getDriver().setReview(listOfReviews));
          customerRepository.save(customerExist);

            return review;

        }

    /********************************************************************
     * Description find the review of the customer by their customer id and booking id it is a repository method

     * @param reviewedBy
     * @param bookingId
     * @return List of reviews
     */
    private List<Review> findByReview(Integer reviewedBy, Integer bookingId) throws ReviewException {


        return this.reviewRepository.findByReviewedByAndBookingId(reviewedBy,bookingId);
    }

    /***************************************************************************
     * Description retrieves the review of the customer by their id
     * method getReviewById
     * @param reviewId
     * @return review
     * @throws ReviewException
     */

    @Override
    public Review getReviewById(Integer reviewId) throws ReviewException {
         Optional<Review> reviewOptional=this.reviewRepository.findById(reviewId);
         if(reviewOptional.isEmpty())
             throw new ReviewException("Id with "+reviewId+" does not exist");
         Review review=reviewRepository.findById(reviewId).get();
         if(review.getBookingId()==null)
             throw new ReviewException("BookingID can not exist");
         if(review.getReviewedBy()==null)
             throw new ReviewException("CustomerId can not exist");
         if(review.getReview()==null)
             throw new ReviewException("Review empty");

//       Optional<Review> review=this.reviewRepository.findById(reviewId);
//       if(review==null) throw ReviewException;
        return this.reviewRepository.findById(reviewId).get();

    }





}
