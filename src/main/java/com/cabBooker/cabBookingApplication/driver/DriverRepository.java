package com.cabBooker.cabBookingApplication.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DriverRepository extends JpaRepository<Driver,Integer> {
    Driver findByDriverVehicleNumber(Integer driverVehicleNumber);

    List<Driver> findByDriverAgencyName(String driverAgencyName);

}
