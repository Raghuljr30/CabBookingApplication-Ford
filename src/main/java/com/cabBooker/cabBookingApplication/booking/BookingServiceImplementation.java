package com.cabBooker.cabBookingApplication.booking;

import com.cabBooker.cabBookingApplication.cab.Cab;

import com.cabBooker.cabBookingApplication.cab.CabRepository;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgencyNotFoundException;
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


    /*************************************************************************************************************
     *Method                                        - bookCabByCabId
     * Descriotion                                  - to book a cab with its id
     * @param cabId                                 - Cab's id that need to be booked
     * @param customerId                            - customer who is booking the cab
     * @param paymentType                           -customer's payment type
     * @return Customer                             -returns the customer with a booking
     * @throws CabAgencyNotFoundException          -this error is thrown if  no cab agency exist with the given Id
     * @throws CustomerNotFoundException           - this error is thrown if the customer with the given id is not found
     * @throws CabNotFoundException                - this error is thrown if the cab with the given id is not found
     * @throws  CabNotAvailableException            - this error is thrown if the cab is current unavailable
     * @throws  CustomerCurrentBookingExistException - this error is thrown if the customer has current booking and trying to book another cab
     * Created By                                  - Raghul
     * Created Date                                 -

     *********************************************************************************************************************/


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
            booking.setBookingOver(false);
            booking.setBookingDate(new Date());
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

    /*************************************************************************************************************
     *Method                                        - bookingOver
     * Descriotion                                  - to complete a current booking of the customer
     * @param customerId                           - customer id whose booking should be closed
     * @param bookingId                             - booking which needs to be closed
     * @return CabAgency                            -returns the cab agency with its associated drivers
     * @throws  CustomerNotFoundException           -this error is thrown if the customer with the given id is not found
     * @throws  BookingNotFoundException            -this error is thrown if customer does not have a current booking with this booking id
     * Created By                                  - Raghul
     * Created Date                                 -

     *********************************************************************************************************************/



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
        bookings.get(0).setBookingOver(true);
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

    /*************************************************************************************************************
     *Method                                        - displayAllCurrentBookingsOfCabAgency
     * Descriotion                                  - to display all the current bookings of the cab agency
     * @param cabAgencyId                            - Cab agency's id
     * @return List<Booking>                          -returns the List of all the current bookings of the cab agency
     * Created By                                  - Raghul
     * Created Date                                 -

     *********************************************************************************************************************/
    @Override
    public List<Booking> displayAllCurrentBookingsOfCabAgency(Integer cabAgencyId) {
       return bookingRepository.findBookingsByCab_CabAgencyIdAndAndBookingOver(cabAgencyId,false);

    }


    /*************************************************************************************************************
     *Method                                        -displayAllBookingsOfCabAgency
     * Descriotion                                  - to display all the bookings of the cab agency
     * @param cabAgencyId                            - Cab agency's id
     * @return List<Booking>                          -returns the List of all the bookings of the cab agency
     * Created By                                  - Raghul
     * Created Date                                 -

     *********************************************************************************************************************/

    @Override
    public List<Booking> displayAllBookingsOfCabAgency(Integer cabAgencyId) {
        return bookingRepository.findBookingsByCab_CabAgencyId(cabAgencyId);
    }


    /*************************************************************************************************************
     *Method                                        -displayAllAvailableCabs
     * Descriotion                                  - to display all the available cabs
     * @return List<Cab>                          -returns the List of all the available cabs
     * Created By                                  - Raghul
     * Created Date                                 -

     *********************************************************************************************************************/

    @Override
    public List<Cab> displayAllAvailableCabs() {
        List<Cab> listOfAvailableCabs=this.cabRepository.findByAvailability(true);
        return listOfAvailableCabs;
    }

    /*************************************************************************************************************
     *Method                                        -searchAvailableCabsByLocation
     * Descriotion                                  - to display all the available cabs based on the location
     * @param fromLocation                          - pick up point of the cab
     * @param toLocation                            - drop point of the cab
     * @return List<Cab>                          -returns the List of all the available cabs in that particular location
     * Created By                                  - Raghul
     * Created Date                                 -

     *********************************************************************************************************************/

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
