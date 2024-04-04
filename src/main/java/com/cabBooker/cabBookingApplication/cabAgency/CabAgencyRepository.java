package com.cabBooker.cabBookingApplication.cabAgency;

import com.cabBooker.cabBookingApplication.cab.Cab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabAgencyRepository extends JpaRepository<CabAgency,Integer> {
    CabAgency findByCabAgencyName(String cabAgencyName);


}
