package com.cabBooker.cabBookingApplication.cab;

import java.util.List;

public interface CabService {
    public Cab registerNewCab(Cab cab);

    public List<Cab> displayAllCabs();

    public Cab mapCabAndDriver(Integer vehicleNumber, Integer driverVehiclenumber);
}
