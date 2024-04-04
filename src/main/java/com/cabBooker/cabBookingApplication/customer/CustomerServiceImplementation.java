package com.cabBooker.cabBookingApplication.customer;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.booking.BookingRepository;
import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabRepository;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImplementation implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;



    @Autowired
    private BookingRepository bookingRepository;

    /*******************************************************
     * Description registration of a new customer
     * @param customer
     * @return customer
     * method registerNewCustomer
     * @throws CustomerException
     */
    @Override
    public Customer registerNewCustomer(Customer customer)throws CustomerException
    {
        if(customer.getMobileNumber()==null||customer.getPassword()==null||customer.getName()==null||customer.getEmail()==null)
            throw new CustomerException("Input fields can't be null");
            return this.customerRepository.save(customer);

    }

    /******************************************************
     * Description display the list of customers who registered
     * method displayAllCustomers
     * @return list of customers
     * @throws CustomerListEmptyException
     */
    @Override
    public List<Customer> displayAllCustomers() throws CustomerListEmptyException {
        List<Customer> customerList=this.customerRepository.findAll();
        if(customerList.isEmpty())
            throw new CustomerListEmptyException("List is empty");
        return this.customerRepository.findAll();
    }

    /*************************************************************
     * Description: to get customer details by their id
     * Method: findCustomer
     * @param customerId
     * @return customer
     * @throws CustomerException
     */
    @Override
    public Customer findCustomer(Integer customerId)throws CustomerException {
        if(customerId==null){
            throw new CustomerException("Customer id can't be null");
        }

        return this.customerRepository.findById(customerId).get();
    }

    /*******************************************************
     * Description retrieves the customer details by their id
     * method getCustomerById
     * @param customerId
     * @return customer
     */
    @Override
    public Customer getCustomerById(Integer customerId) {

        return this.customerRepository.findById(customerId).get();
    }

    /*********************************************************
     * Description admin controller method that retrieves the list of all booking done by the customers
     * method allBookings
     * @return list og bookings
     * @throws CustomerListEmptyException
     */
    @Override
    public List<Booking> allBookings() throws CustomerListEmptyException {

        List<Booking> customerBooking=this.bookingRepository.findAll();
        if(customerBooking.isEmpty())
            throw new CustomerListEmptyException("List Empty");
        return this.bookingRepository.findAll();
    }

    /*************************************************************
     * Description using the id and password customer logins and can view the bookings and other details
     * method viewCustomer
     * @param customerId
     * @param customerPassword
     * @return customer
     * @throws CustomerException
     */
    @Override
    public Customer viewCustomer(Integer customerId, String customerPassword) throws CustomerException{
        Optional<Customer> optionalAccount = this.customerRepository.findById(customerId);
        if(optionalAccount.isEmpty())
            throw new CustomerException("Customer with "+customerId+" does not exist");
        if(customerPassword==null){
            throw new CustomerException("Password can not be null");
        }
        //this.customerRepository.(prodId);
        return optionalAccount.get();
    }


    /**********************************************************
     * description delete the customer by their id
     * method deleteCustomerById
     * @param customerId
     * @return deleted customer
     * @throws CustomerException
     */
    @Override
    public Customer deleteCustomerById(Integer customerId) throws CustomerException{
        Optional<Customer> findCustomer=this.customerRepository.findById(customerId);
      if(!findCustomer.isPresent())throw new CustomerException("Customer with "+customerId+" does not exist");
      this.customerRepository.deleteById(customerId);
      return null;
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

    /*********************************************************
     * Description updates the password of the customer
     * method updateCustomerPsssword
     * @param customerId
     * @param customerPassword
     * @return customer password with update
     * @throws CustomerException
     */
    @Override
    public Customer updateCustomerPassword(Integer customerId, String customerPassword) throws CustomerException {
        Optional<Customer> findCustomer=this.customerRepository.findById(customerId);
        if(findCustomer.isEmpty()){
            throw new CustomerException("Customer with "+customerId+" does not exist");
        }
        if(findCustomer.isPresent()) {
            if (customerPassword == null || customerPassword == "")
                throw new CustomerException("Password Can't be null");

            Customer customerFound = findCustomer.get();
            customerFound.setPassword(customerPassword);


            return this.customerRepository.save(customerFound);
        }
        return null;

    }





//    @Override
//    public Customer updateCustomer(Customer customer) throws CustomerException{
//        if(customer.getMobileNumber()==null||customer.getPassword()==null||customer.getName()==null||customer.getEmail()==null)
//            throw new CustomerException("Existing filed");
//
//
//
//        return this.customerRepository.save(customer);
//    }


    /**************************************************************
     * Description admin control method to display all the list of customers
     * method allCustomers
     * @return customerList
     */
    @Override
    public List<Customer> allCustomers() {
        List<Customer> customerList=this.customerRepository.findAll();
        return customerList;
    }

//    @Override
//    public Customer loginCustomer(String customerName, String customerPassword) {
//       return null;
//    }

    /**************************************************************
     * Description retrieves the details of the particular customer  using their id
     * method viewProfile
     * @param customerId
     * @return customer
     * @throws CustomerException
     */
    @Override
    public Customer viewProfile(Integer customerId) throws CustomerException {
        Optional<Customer> optionalCustomer=this.customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()){
            throw new CustomerException("Customer with "+customerId+" does not exist");
        }

        return this.customerRepository.findById(customerId).get();
    }

    /********************************************************************
     * desription
     * @param customerId
     * @param customerMobileNumber
     * @return
     * @throws CustomerException
     */

    @Override
    public Customer updateCustomerMobile(Integer customerId, Long customerMobileNumber) throws CustomerException{
        if(customerId==null||customerMobileNumber==null){
            throw new CustomerException("Params can't be null");
        }
        Optional<Customer> optionalCustomer=this.customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()){
            throw new CustomerException("Customer with "+customerId+" does not exist");
        }


        Customer customerFound=customerRepository.findById(customerId).get();
        if(customerFound.getMobileNumber()==customerMobileNumber) throw new CustomerException("mobile is same.will not get updated");

        customerFound.setMobileNumber(customerMobileNumber);
        return  customerRepository.save(customerFound);


    }





//    @Override
//    public List<Cab> availableCabs(String cabAgency, String pickUpLocation, String dropLocation, LocalDate bookingDate) {
//        return ;
//    }


}







