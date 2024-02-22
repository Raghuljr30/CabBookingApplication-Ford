package com.cabBooker.cabBookingApplication.booking;

import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabRepository;
import com.cabBooker.cabBookingApplication.customer.Customer;
import com.cabBooker.cabBookingApplication.customer.CustomerRepository;
import com.cabBooker.cabBookingApplication.customer.CustomerService;
import com.cabBooker.cabBookingApplication.payment.Payment;
import com.cabBooker.cabBookingApplication.payment.PaymentRepository;
import com.cabBooker.cabBookingApplication.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingServiceImplementation implements  BookingService{
    @Autowired
    private CabRepository cabRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentService paymentService;
//    @Override
    public Customer bookCab(Customer customer, String pickUpLocation, String dropLocation) {
      Cab cab= this.cabRepository.findByPickUpPointAndDropPoint(pickUpLocation,dropLocation);
      Booking booking=new Booking();

//      booking.setBookingId(UUID.randomUUID().toString());
      booking.setCab(cab);
      booking.setCabNumber(cab.getVehicleNumber());
      booking.setCustomerName(customer.getName());
      booking.setPickUpLocation(pickUpLocation);
      booking.setDropLocation(dropLocation);
      System.out.println(" ----------------------"+booking);
      this.bookingRepository.save(booking);

//    List<Booking> bookings=this.bookingRepository.findAll();
//    customer.setBookings(bookings);
//    List<Booking>bookings=new ArrayList<>();
//    bookings.add(booking);
//    customer.setBookings(bookings);
        customer.setCurrentBookingExist(true);
        mapBookingAndCustomer(customer.getId(), customer.getName());
        return  this.customerRepository.save(customer);



    }

    @Override
    public List<Booking> displayAllBookings() {
        return this.bookingRepository.findAll();
    }

    @Override
    public Customer mapBookingAndCustomer(Integer customerId, String customerName) {

        Customer customer=this.customerRepository.findById(customerId).get();
        List<Booking>bookings= this.bookingRepository.findByCustomerName(customerName);
        System.out.println(customer);
        System.out.println(bookings);
        customer.setBookings(bookings);
        return this.customerRepository.save(customer);


    }

    @Override
    public Customer bookCabByCabId(Integer customerId, Integer cabId,String paymentType) {
        Optional<Customer> customer=this.customerRepository.findById(customerId);
        Optional<Cab> cab=this.cabRepository.findById(cabId);
        Cab cabExist=cab.get();

        Customer customerExist=customer.get();
        Boolean existBooking=customerExist.getCurrentBookingExist();

            System.out.println(existBooking);
            Booking booking=new Booking();
            booking.setCab(cabExist);
            booking.setCabNumber(cabExist.getVehicleNumber());
            booking.setCustomerName(customerExist.getName());
            booking.setPickUpLocation(cabExist.getPickUpPoint());
            booking.setDropLocation(cabExist.getDropPoint());
            Payment payment=this.paymentService.makePayment(customerExist,booking,cabExist,paymentType);
            booking.setPayment(payment);
            System.out.println("fjnjrfnjsnjfejnj");
            this.bookingRepository.save(booking);
            mapBookingAndCustomer(customerId,customerExist.getName());
            return  this.customerRepository.save(customerExist);




    }

    public Customer bookingOver(Integer customerId)
    {

        Customer customer=this.customerRepository.findById(customerId).get();
        customer.setCurrentBookingExist(false);
        return customer;

    }


}
