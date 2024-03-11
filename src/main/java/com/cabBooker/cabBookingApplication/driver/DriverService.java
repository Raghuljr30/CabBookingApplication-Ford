package com.cabBooker.cabBookingApplication.driver;

import java.util.List;
import java.util.Optional;

public interface DriverService {

    public Driver registerNewDriver(Driver driver)throws nullFieldException;


   // Driver getDriverVehicleNumber(Driver driver);

    Optional<Driver> getDriverById(Integer driverId)throws nullFieldException,noChangeException;

    Driver deleteDriverById(Integer driverId) throws nullFieldException;

    Driver updateDriver(Integer driverId,Driver newDriver) throws nullFieldException;

    List<Driver> listAllDrivers()throws emptyListException;

    List<Driver> getDriversByAgencyName(String cabAgencyName) throws emptyListException, nullFieldException;

    Driver changeAgency(Integer driverId, String driverAgencyName,Integer driverAgencyId)throws noChangeException,nullFieldException;

    Driver changeMobileNumber(Integer driverId, Long driverMobileNumber)throws noChangeException,nullFieldException;

    Driver changePassword(Integer driverId, String password)throws noChangeException,nullFieldException;

    List<Driver> displayAllDriver();


    //   Driver changeAgencyId(Integer driverAgencyId, Integer cabAgencyId);
}
