package com.cabBooker.cabBookingApplication.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {

    List<Booking> findByCustomerName(String customerName);
    List<Booking> findBookingsByCab_CabAgencyIdAndAndBookingOver (Integer cabAgencyId,Boolean bookingOver);
    List<Booking> findBookingsByCab_CabAgencyId(Integer cabAgencyId);

}
