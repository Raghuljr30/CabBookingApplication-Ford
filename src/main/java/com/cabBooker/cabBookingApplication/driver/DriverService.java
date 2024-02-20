package com.cabBooker.cabBookingApplication.driver;

import java.util.List;

public interface DriverService {

    public Driver registerNewDriver(Driver driver);
    public List<Driver> displayAllDriver();

}
