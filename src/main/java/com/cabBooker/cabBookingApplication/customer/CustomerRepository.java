package com.cabBooker.cabBookingApplication.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findByIdAndEmailAndPassword(Integer id,String email,String password);
}
