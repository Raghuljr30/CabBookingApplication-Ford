package com.cabBooker.cabBookingApplication.driver;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgency;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgencyNotFoundException;
import com.cabBooker.cabBookingApplication.review.Review;

import java.util.List;
import java.util.Optional;

public interface DriverService {

    public Driver registerNewDriver(DriverDto driverDto)throws nullFieldException;


    Optional<Driver> getDriverById(Integer driverId)throws nullFieldException,noChangeException;

    Driver deleteDriverById(Integer driverId) throws nullFieldException;

    Driver updateDriver(Integer driverId,Driver newDriver) throws nullFieldException;

    List<Driver> listAllDrivers()throws emptyListException;

    List<Driver> getDriversByAgencyName(String cabAgencyName) throws emptyListException, nullFieldException;

    Driver changeAgency(Integer driverId, String driverAgencyName,Integer driverAgencyId)throws noChangeException,nullFieldException;

    Driver changeMobileNumber(Integer driverId, Long driverMobileNumber)throws noChangeException,nullFieldException;

    Driver changePassword(Integer driverId, String password)throws noChangeException,nullFieldException;

    Driver loginDriver(Integer driverId, String password);
    List<Driver> displayAllDriver();


    List<Review> getDriversReview(Integer driverId);


    Cab mapCabAndDriver(Integer vehicleNumber, Integer driverVehiclenumber);

    CabAgency mapCabAgencyAndCabUsingId(Integer cabAgencyId) throws nullFieldException;



    List<Booking> getDriversBookings(Integer driverId) throws nullFieldException;

    Driver changeMail(Integer driverId, String mail) throws nullFieldException,noChangeException;

    Driver changeName(Integer driverId, String name)throws nullFieldException,noChangeException;
}
