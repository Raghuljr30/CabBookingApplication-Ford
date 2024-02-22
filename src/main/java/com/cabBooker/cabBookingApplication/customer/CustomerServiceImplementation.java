package com.cabBooker.cabBookingApplication.customer;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.booking.BookingRepository;
import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabRepository;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImplementation implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CabRepository cabRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Customer registerNewCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public List<Customer> displayAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer findCustomer(Integer customerId) {
        return this.customerRepository.findById(customerId).get();
    }


    @Override
    public Customer getCustomerById(Integer customerId) {
        return this.customerRepository.findById(customerId).get();
    }

    @Override
    public List<Booking> allBookings(List<Booking> customerBookings) {

        return this.bookingRepository.findAll();
    }

    @Override
    public Customer viewCustomer(Integer customerId, String customerPassword) {
        Optional<Customer> optionalAccount = this.customerRepository.findById(customerId);
        //this.customerRepository.(prodId);
        return optionalAccount.get();
    }



    @Override
    public Customer deleteCustomerById(Integer customerId) {
       Customer optionalCustomer=this.customerRepository.findById(customerId).get();
        this.customerRepository.deleteById(customerId);
        return optionalCustomer;
    }

//    @Override
//    public Customer updateCustomerEmail(Integer customerId, String customerEmail) {
//     Optional<Customer> findCustomer=this.customerRepository.findById(customerId);
//     Customer customerfound= findCustomer.get();
//     customerfound.setEmail(customerEmail);
//     return this.customerRepository.save(customerfound);
//
//
//    }

    @Override
    public Customer updateCustomerPassword(Integer customerId, String customerPassword) {
        Optional<Customer> findCustomer=this.customerRepository.findById(customerId);
        Customer customerFound=findCustomer.get();
        customerFound.setPassword(customerPassword);
        return this.customerRepository.save(customerFound);
    }

    @Override
    public List<Booking> filterBookingByFair(Double cabFair) {
        return null;
    }

    @Override
    public Double cabFairById(Integer cabAgencyId, Integer cabId) {
        return null;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomerMobile(Integer customerId, Long customerMobileNumber) {
        Optional<Customer> findCustomer=this.customerRepository.findById(customerId);

        Customer customerfound=findCustomer.get();
        customerfound.setMobileNumber(customerMobileNumber);
        return  this.customerRepository.save(customerfound);


    }





//    @Override
//    public List<Cab> availableCabs(String cabAgency, String pickUpLocation, String dropLocation, LocalDate bookingDate) {
//        return ;
//    }


}







