package com.cabBooker.cabBookingApplication.cab;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CabRepository extends JpaRepository<Cab,Integer> {

    Cab findByVehicleNumber(Integer vehicleNumber);

    List<Cab> findByAgencyName(String agencyName);

    Cab findByPickUpPointAndDropPoint(String pickUpLocation,String dropLocation);
}
