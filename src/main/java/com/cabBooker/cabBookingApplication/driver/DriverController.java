package com.cabBooker.cabBookingApplication.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DriverController {

    @Autowired
    private DriverService driverService;


    /** Driver Registration */
    @PostMapping("/driver")
    public Driver registerDriver(@RequestBody Driver driver) throws nullFieldException
    {
        return this.driverService.registerNewDriver(driver);
    }

    @DeleteMapping("driver/id/{driverId}")
    public Driver deleteDriverById(@PathVariable("driverId") Integer driverId) throws nullFieldException
    {
        return this.driverService.deleteDriverById(driverId);
    }

    @GetMapping("driver-list")
    public List<Driver> listAllDrivers()throws emptyListException
    {
        return driverService.listAllDrivers();
    }

    @GetMapping("driver/{id}")
    public Optional<Driver> getDriverById(@PathVariable("id") Integer driverId) throws nullFieldException,noChangeException {
        return this.driverService.getDriverById(driverId);
    }

    @GetMapping("driver/agency/search/{agencyName}")
    public List<Driver> getDriversByAgencyName(@PathVariable("agencyName") String  cabAgencyName) throws emptyListException, nullFieldException {
        return this.driverService.getDriversByAgencyName(cabAgencyName);
    }


    @PutMapping("driver/update/{Id}")
    public Driver updateDriver(@PathVariable("Id") Integer driverId,@RequestBody Driver newDriver)throws nullFieldException
    {
        return this.driverService.updateDriver(driverId,newDriver);
    }

    @PatchMapping("driver/change/{Id}/{agencyName}/{driverAgencyId}")
    public Driver changeAgency(@PathVariable("Id") Integer driverId,@PathVariable("agencyName") String driverAgencyName ,@PathVariable("driverAgencyId") Integer driverAgencyId)throws noChangeException,nullFieldException
    {
        return this.driverService.changeAgency(driverId,driverAgencyName,driverAgencyId);
    }

    @PatchMapping("driver/change/{Id}/{mobileNumber}")
    public Driver changeMobileNumber(@PathVariable("Id") Integer driverId,@PathVariable("mobileNumber") Long driverMobileNumber)throws noChangeException, nullFieldException
    {
        return this.driverService.changeMobileNumber(driverId,driverMobileNumber);
    }

    @PatchMapping("driver/change/{Id}/{password}")
    public Driver changePassword(@PathVariable("Id") Integer driverId,@PathVariable("password") String password)throws noChangeException, nullFieldException
    {
        return this.driverService.changePassword(driverId,password);
    }



//    @PostMapping("/driverupdate")
//    public Driver getDriverVehicleNumber(@PathVariable )
//    {
//        return this.driverService.getDriverVehicleNumber(Integer number);
//    }



}
