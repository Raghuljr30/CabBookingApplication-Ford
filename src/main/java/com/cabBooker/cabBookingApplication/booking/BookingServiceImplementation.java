package com.cabBooker.cabBookingApplication.booking;

import com.cabBooker.cabBookingApplication.cab.Cab;

import com.cabBooker.cabBookingApplication.cab.CabRepository;
import com.cabBooker.cabBookingApplication.customer.Customer;
import com.cabBooker.cabBookingApplication.customer.CustomerRepository;
import com.cabBooker.cabBookingApplication.payment.Payment;
import com.cabBooker.cabBookingApplication.payment.PaymentRepository;
import com.cabBooker.cabBookingApplication.payment.PaymentService;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
//    public Customer bookCab(Customer customer, String pickUpLocation, String dropLocation) {
//      Cab cab= this.cabRepository.findByPickUpPointAndDropPoint(pickUpLocation,dropLocation);
//
//      Booking booking=new Booking();
//
////      booking.setBookingId(UUID.randomUUID().toString());
//      booking.setCab(cab);
//      booking.setCabNumber(cab.getVehicleNumber());
//      booking.setCustomerName(customer.getName());
//      booking.setPickUpLocation(pickUpLocation);
//      booking.setDropLocation(dropLocation);
//      this.bookingRepository.save(booking);
//
//
//        customer.setCurrentBookingExist(true);
//        mapBookingAndCustomer(customer.getId(), customer.getName());
//        return  this.customerRepository.save(customer);
//
//
//
//    }

    @Override
    public List<Booking> displayAllBookings()
    {
        return this.bookingRepository.findAll();
    }


    public void mapBookingAndCustomer(Integer customerId, String customerName) {

        Customer customer=this.customerRepository.findById(customerId).get();
        List<Booking>bookings= this.bookingRepository.findByCustomerName(customerName);
        customer.setCurrentBookingExist(true);
        System.out.println(customer);
        System.out.println(bookings);
        customer.setBookings(bookings);
        this.customerRepository.save(customer);


    }

    @Override
    public Customer bookCabByCabId(Integer customerId, Integer cabId,String paymentType) throws CustomerNotFoundException, CabNotFoundException ,CustomerCurrentBookingExistException,CabNotAvailableException{
        Optional<Customer> customer=this.customerRepository.findById(customerId);
        if(!customer.isPresent()) throw new CustomerNotFoundException("Customer not Exist");
        Customer customerExist=customer.get();
        Optional<Cab> cab=this.cabRepository.findById(cabId);
        if(!cab.isPresent()) throw new CabNotFoundException("cab not found");
        Cab cabExist= cab.get();

        if(!checkIfCurrentBookingNotExist(customerExist)) throw new CustomerCurrentBookingExistException("Booking exist currently.Cannot book now");
        if(!checkIfCabAvailable(cabExist)) throw new CabNotAvailableException("cab not available");
        if(checkIfCurrentBookingNotExist(customerExist) && checkIfCabAvailable(cabExist)) {

            Cab cabExistAfterChangingAvailablity=changeCabAvailablity(cab.get());
            Booking booking = new Booking();
            booking.setCab(cabExistAfterChangingAvailablity);
            booking.setCabNumber(cabExistAfterChangingAvailablity.getVehicleNumber());
            booking.setCustomerName(customerExist.getName());
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
    public Customer bookingOver(Integer customerId,Integer bookingId) throws CustomerNotFoundException, BookingNotFoundException
    {
       Optional <Customer> customer=this.customerRepository.findById(customerId);
       if(!customer.isPresent()) throw new CustomerNotFoundException("customer not found");

        Customer customerExist=customer.get();
        System.out.println("customer"+ customer);
        customerExist.setCurrentBookingExist(false);
        List<Booking> bookings= customerExist.getBookings().stream().
                filter((booking)-> booking.getBookingId().equals(bookingId)).collect(Collectors.toList());
        if(bookings.size()==0) throw new BookingNotFoundException("booking not found");

        System.out.println("booking list------"+ bookings);
        Cab cab= bookings.get(0).getCab();
        cab.setAvailability(true);
        this.cabRepository.save(cab);
        return this.customerRepository.save(customerExist);
    }

    @Override
    public Booking deleteBookingById(Integer bookingId, Integer customerId) throws CustomerNotFoundException,BookingNotFoundException{
        Optional <Customer> customer=this.customerRepository.findById(customerId);
        if(!customer.isPresent()) throw new CustomerNotFoundException("customer not found");
        Customer customerExist= customer.get();
        Optional<Booking> booking=this.bookingRepository.findById(bookingId);
        if(!booking.isPresent()) throw new BookingNotFoundException("Booking not found");
        Booking bookingExist= booking.get();

        Cab bookedCab=bookingExist.getCab();
        bookedCab.setAvailability(true);
        this.cabRepository.save(bookedCab);
        customerExist.getBookings().remove(bookingExist);
        this.customerRepository.save(customerExist);

        this.bookingRepository.deleteById(bookingId);



        return null;
    }

    @Override
    public List<Cab> displayAllAvailableCabs() {
        List<Cab> listOfAvailableCabs=this.cabRepository.findByAvailability(true);
        return listOfAvailableCabs;
    }

    @Override
    public List<Cab> searchAvailableCabsByLocation(String fromLocation, String toLocation) {
        List<Cab> listOfCabsAvailableBasedOnLocation=this.cabRepository.findByPickUpPointAndDropPointAndAvailability(fromLocation,toLocation,true);
        return listOfCabsAvailableBasedOnLocation;
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
