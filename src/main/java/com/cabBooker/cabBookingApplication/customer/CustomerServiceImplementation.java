package com.cabBooker.cabBookingApplication.customer;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.booking.BookingRepository;
import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabRepository;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

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
    public Boolean loginCustomer(Integer id, String email, String password) {

        Customer customerRegistered= customerRepository.findByIdAndEmailAndPassword(id,email,password);
        if(customerRegistered==null)
        {
            return false;
        }
        return true;


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

    @Override
    public Booking displayCurrentBooking(Integer customerId) {
        Optional<Customer>customer= this.customerRepository.findById(customerId);

        Customer customerExist=customer.get();
        if(customerExist.getCurrentBookingExist())
        {
            List<Booking> customerBookings= customerExist.getBookings();
            Booking currentBooking=customerBookings.get(customerBookings.size()-1);
            return currentBooking;
        }
        return null;


    }
}



