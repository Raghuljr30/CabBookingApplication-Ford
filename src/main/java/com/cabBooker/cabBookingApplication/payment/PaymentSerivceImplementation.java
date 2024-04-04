package com.cabBooker.cabBookingApplication.payment;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentSerivceImplementation implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public Payment makePayment(Customer customer, Booking booking, Cab cab, String paymentType) {

        String paidBy=customer.getName();
        Double paidAmount=cab.getFair();

        Payment payment=new Payment();
        payment.setPaymentType(paymentType);
        payment.setPaidAmount(paidAmount);
        payment.setPaidBy(paidBy);
        return this.paymentRepository.save(payment);




    }
}
