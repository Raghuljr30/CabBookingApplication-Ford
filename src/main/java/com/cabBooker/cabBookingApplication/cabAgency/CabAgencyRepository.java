package com.cabBooker.cabBookingApplication.cabAgency;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CabAgencyRepository extends JpaRepository<CabAgency,Integer> {
    CabAgency findByCabAgencyName(String cabAgencyName);

    CabAgency findByCabAgencyIdAndCabAgencyEmailAndCabAgencyPassword(Integer cabAgencyId,String cabAgencyEmail,String cabAgencyPassword);

}
