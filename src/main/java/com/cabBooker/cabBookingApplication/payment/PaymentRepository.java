package com.cabBooker.cabBookingApplication.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
