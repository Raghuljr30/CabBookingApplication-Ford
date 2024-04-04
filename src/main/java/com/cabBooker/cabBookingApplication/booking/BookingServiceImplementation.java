package com.cabBooker.cabBookingApplication.booking;

import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabRepository;
import com.cabBooker.cabBookingApplication.customer.Customer;
import com.cabBooker.cabBookingApplication.customer.CustomerRepository;
import com.cabBooker.cabBookingApplication.payment.Payment;
import com.cabBooker.cabBookingApplication.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImplementation implements  BookingService{
    @Autowired
    private CabRepository cabRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PaymentService paymentService;
    @Override
    public Customer bookCab(Customer customer,String cutomerEmail, String pickUpLocation, String dropLocation) {
      List<Cab> cab= this.cabRepository.findByPickUpPointAndDropPoint(pickUpLocation,dropLocation);
      System.out.println("-------------------"+cab);
      Booking booking=new Booking();
      booking.setCab(cab.get(0));
      booking.setCabNumber(cab.get(0).getVehicleNumber());
      booking.setCustomerName(customer.getName());
      booking.setPickUpLocation(pickUpLocation);
      booking.setDropLocation(dropLocation);
      this.bookingRepository.save(booking);
      List<Booking>bookings=new ArrayList<>();
      bookings.add(booking);
      customer.setBookings(bookings);

      return  this.customerRepository.save(customer);



    }
    @Override
    public Customer bookCabByCabId(Integer customerId, Integer cabId,String paymentType) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);

        Customer customerExist = customer.get();
        Optional<Cab> cab = this.cabRepository.findById(cabId);

        Cab cabExist = cab.get();


        if (checkIfCurrentBookingNotExist(customerExist) && checkIfCabAvailable(cabExist)) {

            Cab cabExistAfterChangingAvailablity = changeCabAvailablity(cab.get());
            Booking booking = new Booking();
            booking.setCab(cabExistAfterChangingAvailablity);
            booking.setCabNumber(cabExistAfterChangingAvailablity.getVehicleNumber());
            booking.setBookingId(customerExist.getId());
            booking.setCustomerName(customerExist.getName());
            booking.setCustomerId(customerExist.getId());
            booking.setPickUpLocation(cabExistAfterChangingAvailablity.getPickUpPoint());
            booking.setDropLocation(cabExistAfterChangingAvailablity.getDropPoint());
            Payment payment = this.paymentService.makePayment(customerExist, booking, cabExistAfterChangingAvailablity, paymentType);
            booking.setPayment(payment);
            this.bookingRepository.save(booking);
            mapBookingAndCustomer(customerId, customerExist.getName());
            return this.customerRepository.save(customerExist);
        }


        return null;


    }

    @Override
    public List<Booking> bookingByCustomer(Integer customerId) {
        Optional<Customer> customerOptional=this.customerRepository.findById(customerId);
        Customer customerExists=customerOptional.get();
        List<Booking> custBooking=customerExists.getBookings();


        return custBooking;


    }




//    @Override
//    public List<Booking> listOfBookingById(Integer customerId) {
//        return this.bookingRepository.findByCustomerId(customerId);
//    }

    public void mapBookingAndCustomer(Integer customerId, String customerName) {

        Customer customer=this.customerRepository.findById(customerId).get();
        List<Booking>bookings= this.bookingRepository.findByCustomerName(customerName);
        customer.setCurrentBookingExist(true);
        System.out.println(customer);
        System.out.println(bookings);
        customer.setBookings(bookings);
        this.customerRepository.save(customer);


    }
    public Boolean checkIfCurrentBookingNotExist(Customer customer)
    {
        System.out.println(customer.getCurrentBookingExist());

        if(customer.getCurrentBookingExist())
        {
            return false;
        }
        return true;
    }

    public Cab changeCabAvailablity(Cab cab)
    {
        Boolean availablity= cab.getAvailability() ? false :true;
        cab.setAvailability(availablity);
        this.cabRepository.save(cab);
        System.out.println("--------------"+cab);
        return this.cabRepository.save(cab);
    }

    public Boolean checkIfCabAvailable(Cab cab)
    {
        if(cab.getAvailability()==true)
        {
            return true;
        }
        return false;
    }
    }
