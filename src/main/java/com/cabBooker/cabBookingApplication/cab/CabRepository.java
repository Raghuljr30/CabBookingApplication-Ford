package com.cabBooker.cabBookingApplication.cab;

import org.aspectj.weaver.loadtime.definition.LightXMLParser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CabRepository extends JpaRepository<Cab,Integer> {

    Cab findByVehicleNumber(Integer vehicleNumber);

    List<Cab> findByAgencyName(String agencyName);

//    Cab findByPickUpPointAndDropPoint(String pickUpLocation,String dropLocation);
    List<Cab> findByCabAgencyId(Integer cabAgencyId);

    List<Cab> findByAvailability(Boolean availability);

    List<Cab> findByPickUpPointAndDropPointAndAvailability(String pickUpPoint, String dropPoint,Boolean availability);

    List<Cab> findCabByCabAgencyId(Integer cabAgencyId);
    List<Cab> findByCabAgencyIdAndAvailability(Integer cabAgencyId, Boolean availability);
}
