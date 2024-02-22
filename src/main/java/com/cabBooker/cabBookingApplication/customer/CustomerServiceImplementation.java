package com.cabBooker.cabBookingApplication.customer;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.booking.BookingRepository;
import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService{
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
    public CustomerDto deleteCustomerById(Integer customerId) {
        Customer deletedCustomer= this.customerRepository.findById(customerId).get();
        CustomerDto customerDto=new CustomerDto();
        customerDto.setId(deletedCustomer.getId());
        customerDto.setName(deletedCustomer.getName());
        customerDto.setEmail(deletedCustomer.getEmail());
        customerDto.setMobileNumber(deletedCustomer.getMobileNumber());
        customerDto.setCurrentBookingExist(deletedCustomer.getCurrentBookingExist());
        customerDto.setPassword(deletedCustomer.getPassword());
//        CustomerDto deletedCustomerDto=deletedCustomer;
        this.customerRepository.deleteById(customerId);
       return customerDto;
    }
}



