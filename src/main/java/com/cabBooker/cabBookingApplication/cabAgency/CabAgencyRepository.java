package com.cabBooker.cabBookingApplication.cabAgency;

import com.cabBooker.cabBookingApplication.cab.Cab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabAgencyRepository extends JpaRepository<CabAgency,Integer> {
    CabAgency findByCabAgencyName(String cabAgencyName);

    CabAgency findByCabAgencyIdAndCabAgencyEmailAndCabAgencyPassword(Integer cabAgencyId,String cabAgencyEmail,String cabAgencyPassword);
    CabAgency findByCabAgencyEmail(String email);

}
