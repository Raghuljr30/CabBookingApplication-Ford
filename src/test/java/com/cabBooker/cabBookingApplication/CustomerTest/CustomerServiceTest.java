package com.cabBooker.cabBookingApplication.CustomerTest;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.booking.BookingRepository;
import com.cabBooker.cabBookingApplication.customer.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@SpringBootTest

public class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerService customerService;


    @BeforeEach
    public void createCustomer()
    {
//        Booking booking=Booking.builder().bookingId(1).build();
//        List<Booking>bookings=new ArrayList<>();
//        bookings.add(booking);
//        this.bookingRepository.save(booking);



    }

    @Test
    public void testRegisterNewCustomer() {
      try{
          customerService.registerNewCustomer(Customer.builder().id(null).bookings(null).email(null).mobileNumber(null).name(null).password(null).build());
      } catch (CustomerException e){
          Assertions.assertEquals("Input fields can't be null",e.getMessage());
      }
    }
    @Test
    public void testWithoutNullRegisterCustomer(){
        try{
            customerService.registerNewCustomer(Customer.builder().id(1).mobileNumber(9794L).email("dhkfd").name("ejhf").password("123").build());
        } catch (CustomerException e) {
            Assertions.assertEquals("Input fileds can't be null",e.getMessage());
        }
    }

    @Test
    public void testListCustomer()  {

        try {

            Customer customerCreate=customerService.registerNewCustomer(Customer.builder().password("123").name("abc").id(1).email("adc").mobileNumber(89735L).build());
            List<Customer> customerList = this.customerService.displayAllCustomers();
            Assertions.assertNotNull(customerList);
            Assertions.assertNotEquals(0,customerList.size());

        } catch ( CustomerListEmptyException e ) {
            Assertions.fail(e.getMessage());
        } catch (CustomerException e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    public void testCustomer() {
        try {
            customerService.registerNewCustomer(Customer.builder().id(1).password("jdn").name("emmf").email("fkhkfh").mobileNumber(948585L).build());
            customerService.viewCustomer(1, "jdn");
            // Assertions.assertNotNull(customer);
            //  Assertions.assertEquals(this.customerRepository.findById(1).get().getPassword(),customer.getPassword());


        } catch (CustomerException e) {
            Assertions.assertEquals("No id found", e.getMessage());
        }
    }


//    @Test
//    public  void testUpdateCustomer(){
//        try {
//            Customer customer=customerService.registerNewCustomer(Customer.builder().id(1).name("tamil")
//                    .email("dcnhdbc").mobileNumber(99987L)
//                    .bookings(null).password("jdn").build());
//            Assertions.assertNotNull(customer);
//            customer.setPassword("abc");
//
//            Customer updateCustomer=customerService.updateCustomer(customer);
//            Assertions.assertNotEquals(customer.getPassword(),updateCustomer.getPassword());
//            Assertions.assertNotNull(updateCustomer);
//        } catch (CustomerMissingInputFieldException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Test
    public void testDeleteCustomer(){
        try {
           customerService.deleteCustomerById(1053);
            Assertions.assertNull(customerRepository.findById(1053));
        } catch (CustomerException e) {
            Assertions.assertEquals("No Customer found",e.getMessage());
        }

    }




        @Test
        public void testUpdateCustomerMobile () {
        try{
            Customer customerCreate=customerService.registerNewCustomer(Customer.builder().password("123").name("abc").id(1).email("adc").mobileNumber(89735L).build());
            customerService.updateCustomerMobile(null,null);

        } catch (CustomerException e){
            Assertions.assertEquals("Params can't be null",e.getMessage());
        }
        }

        @Test
        public void test_Mobile_Number_Updated(){
            try {
                Customer customerCreate=customerService.registerNewCustomer(Customer.builder().password("123").name("abc").id(4).email("adc").mobileNumber(89735L).build());
                customerService.updateCustomerMobile(1,12345L);
                Customer updateMobile=customerRepository.findById(1).get();
                Assertions.assertEquals(12345L,updateMobile.getMobileNumber());
            } catch (CustomerException e) {
                throw new RuntimeException(e);
            }
        }

        @Test
        public void test_Password_Updated(){
            try {
                Customer customerCreate=customerService.registerNewCustomer(Customer.builder().password("123").name("abc").id(1).email("adc").mobileNumber(89735L).build());
                customerService.updateCustomerPassword(1,"1234");
                Customer updatePassword=customerRepository.findById(1).get();
                Assertions.assertEquals("1234",updatePassword.getPassword());

            } catch (CustomerException e) {
                Assertions.assertEquals("already existing password",e.getMessage());
            }


        }
        @Test
        public void testFindCustomer(){
        try {
            customerService.findCustomer(null);
        } catch (CustomerException e){
            Assertions.assertEquals("Driver id can't be null",e.getMessage());
        }


        }

        @Test
    public void testViewCustomer(){
            try {
                Customer customerCreate=customerService.registerNewCustomer(Customer.builder().password("123").name("abc").id(1).email("adc").mobileNumber(89735L).build());
               Customer customer= customerService.viewCustomer(1,"123");
               Assertions.assertNotNull(this.customerRepository.findById(10));
            } catch (CustomerException e) {
                Assertions.assertEquals("no data found",e.getMessage());
            }
        }
    }



