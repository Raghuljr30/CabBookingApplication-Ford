package com.cabBooker.cabBookingApplication.customer;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.booking.BookingRepository;
import com.cabBooker.cabBookingApplication.booking.BookingService;
import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgency;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgencySerivce;
//import com.cabBooker.cabBookingApplication.review.ReviewRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ClientInfoStatus;
import java.time.LocalDate;
import java.util.List;
@CrossOrigin({"http://localhost:3000","http://localhost:4200"})
@RestController
public class CustomerController {


    @Autowired
    private CustomerService customerService;


    /** Customer Registration*/
    @PostMapping("/customer/register")
    public Customer registerCustomer( @Valid @RequestBody Customer customer) throws CustomerException
    {
        return  this.customerService.registerNewCustomer(customer);
    }
  /**list of booking of the customer **/
   @GetMapping("/customer/bookings")
    public List<Booking> allBookings() throws CustomerListEmptyException{
        return this.customerService.allBookings();

   }
 /** retrieves the list of customers **/
   @GetMapping("/customers/view")
   public List<Customer> allCustomers(){
        return this.customerService.allCustomers();
   }
 /** retrieves the profile of the customer**/
   @GetMapping("/customer/{id}")
   public Customer viewProfile(@PathVariable("id") Integer customerId) throws CustomerException{
        return this.customerService.viewProfile(customerId);
   }
 /**login for the customer **/
 @GetMapping("/customer/login/{id}/{password}")
    public Customer viewCustomer(@PathVariable("id") Integer customerId,@PathVariable("password") String customerPassword)throws CustomerException{
        return this.customerService.viewCustomer(customerId,customerPassword);
    }
//    @GetMapping("/customer/Login/{name}/{password}")
//    public Customer loginCustomer(@PathVariable("name") String customerName,@PathVariable("password")String customerPassword){
//        return this.customerService.loginCustomer(customerName,customerPassword);
//
//    }
  /**  updates the mobile number of the customer**/
    @PatchMapping("/customer/mobile")
    public Customer updateCustomerMobile(@Valid @RequestBody CustomerDto customerDto)throws CustomerException{

        return this.customerService.updateCustomerMobile(customerDto.getId(),customerDto.getMobileNumber());
    }
//    @PatchMapping("/updateCustomerEmail/{id}/{email}")
//    public Customer updateCustomerEmail(@PathVariable("id") Integer customerId,@PathVariable("email") String customerEmail){
//
//        return  this.customerService.updateCustomerEmail(customerId,customerEmail);
//    }
    /**  updates the password  of the customer**/
    @PatchMapping("/customer/password")
    public Customer updateCustomerPassword(@Valid @RequestBody CustomerDto customerDto) throws CustomerException{

        return  this.customerService.updateCustomerPassword(customerDto.getId(),customerDto.getPassword());
    }
 /** deletes the customer from the repository**/
    @DeleteMapping("/customer/{id}")
    public Customer deleteCustomerByID(@PathVariable("id") Integer customerId) throws CustomerException{
        return this.customerService.deleteCustomerById(customerId);
    }

//    @GetMapping("/customer/filterByFair/{fair}")
//    public  List<Booking> filterBookingByFair(@PathVariable("fair") Double cabFair) throws CustomerNoObjectFoundException{
//        return this.customerService.filterBookingByFair(cabFair);
//    }

//    @GetMapping("/cabFair/{agencyId}/{cabId}")
//    public Double cabFairById(@PathVariable("AgencyId") Integer cabAgencyId,@PathVariable("cabId") Integer cabId){
//        return this.customerService.cabFairById(cabAgencyId,cabId);
//    }



}
