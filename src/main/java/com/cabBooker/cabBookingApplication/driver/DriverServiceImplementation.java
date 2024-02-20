package com.cabBooker.cabBookingApplication.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DriverServiceImplementation implements  DriverService{

    @Autowired
    private DriverRepository driverRepository;
    @Override
    public Driver registerNewDriver(Driver driver) {
       return this.driverRepository.save(driver);
    }

    @Override
    public List<Driver> displayAllDriver() {
        return this.driverRepository.findAll();
    }
}
