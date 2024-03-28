package com.cabBooker.cabBookingApplication.driver;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgency;
import com.cabBooker.cabBookingApplication.review.Review;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin({"http://localhost:4200","http://localhost:3000"})
@Validated
@RestController
public class DriverController {

    @Autowired
    private DriverService driverService;

    /** Driver Registration */
    @PostMapping("/driver/register")
    public Driver registerDriver(@Valid @RequestBody DriverDto driverDto) throws
            nullFieldException
    {
        return this.driverService.registerNewDriver(driverDto);
    }

    @PostMapping("/driver/login/{driverId}/{password}")
    public Driver loginDriver( @PathVariable("driverId") Integer driverId, @PathVariable ("password") String password)
    {
        return this.driverService.loginDriver(driverId,password);
    }



    @DeleteMapping("driver/delete/{driverId}")
    public Driver deleteDriverById(@Valid @PathVariable("driverId") Integer driverId) throws nullFieldException
    {
        return this.driverService.deleteDriverById(driverId);
    }

    @GetMapping("driver/list")
    public List<Driver> listAllDrivers()throws emptyListException
    {
        return driverService.listAllDrivers();
    }
//    @GetMapping("driver/earnings/{driverId}")
//    public Double totalEarning(@PathVariable("driverId") Integer driverId)throws emptyListException
//    {
//        return driverService.totalEarning(driverId);
//    }

    @GetMapping("driver/id/{id}")
    public Optional<Driver> getDriverById(@PathVariable("id") Integer driverId) throws nullFieldException,noChangeException {
        return this.driverService.getDriverById(driverId);
    }

    @GetMapping("driver/agency/{agencyName}")
    public List<Driver> getDriversByAgencyName(@PathVariable("agencyName") String  cabAgencyName) throws emptyListException, nullFieldException {
        return this.driverService.getDriversByAgencyName(cabAgencyName);
    }

    @GetMapping("driver/review/{driverId}")
    public List<Review> getDriversReviews( @PathVariable("driverId")Integer driverId) throws emptyListException, nullFieldException {
        return this.driverService.getDriversReview(driverId);
    }

    @GetMapping("driver/bookings/{driverId}")
    public List<Booking> getDriversBookings( @PathVariable("driverId")Integer driverId) throws emptyListException, nullFieldException {
        return this.driverService.getDriversBookings(driverId);
    }


    @PutMapping("driver/update/{Id}")
    public Driver updateDriver( @PathVariable("Id") Integer driverId,@RequestBody Driver newDriver)throws nullFieldException
    {
        return this.driverService.updateDriver(driverId,newDriver);
    }

    @PatchMapping("driver/agency/{Id}/{agencyName}/{driverAgencyId}")
    public Driver changeAgency( @PathVariable("Id") Integer driverId,@PathVariable("agencyName") String driverAgencyName ,@PathVariable("driverAgencyId") Integer driverAgencyId)throws noChangeException,nullFieldException
    {
        return this.driverService.changeAgency(driverId,driverAgencyName,driverAgencyId);
    }

    @PatchMapping("driver/mobilenumber/{Id}/{mobileNumber}")
    public Driver changeMobileNumber(@PathVariable("Id") Integer driverId,@PathVariable("mobileNumber") Long driverMobileNumber)throws noChangeException, nullFieldException
    {
        return this.driverService.changeMobileNumber(driverId,driverMobileNumber);
    }

    @PatchMapping("driver/password/{Id}/{password}")
    public Driver changePassword(@PathVariable("Id") Integer driverId,@PathVariable("password") String password)throws noChangeException, nullFieldException
    {
        return this.driverService.changePassword(driverId,password);
    }

    @PatchMapping("driver/name/{Id}/{name}")
    public Driver changeName( @PathVariable("Id") Integer driverId,@PathVariable("name") String name)throws noChangeException, nullFieldException
    {
        return this.driverService.changeName(driverId,name);
    }

    @PatchMapping("driver/mail/{Id}/{mail}")
    public Driver changeMail( @PathVariable("Id") Integer driverId,@PathVariable("mail") String mail)throws noChangeException, nullFieldException
    {
        return this.driverService.changeMail(driverId,mail);
    }

}
