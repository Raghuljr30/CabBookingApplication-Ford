package com.cabBooker.cabBookingApplication.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins ={ "http://localhost:4200","http://localhost:3000"})
@RestController
public class DriverController {

    @Autowired
    private DriverService driverService;

    /** Driver Registration */
    @PostMapping("/driver")
    public Driver registerDriver(@RequestBody Driver driver)
    {
        return this.driverService.registerNewDriver(driver);
    }
}
