package com.cabBooker.cabBookingApplication.cab;

import com.cabBooker.cabBookingApplication.driver.Driver;
import com.cabBooker.cabBookingApplication.driver.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CabServiceImplementation implements CabService{

    @Autowired
    private CabRepository cabRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Cab registerNewCab(Cab cab) {
       return this.cabRepository.save(cab);
    }

    @Override
    public List<Cab> displayAllCabs() {
        return this.cabRepository.findAll();
    }

    public List<Cab> availableCabs(){
        return this.cabRepository.findAll();
    }

    @Override
    public Cab mapCabAndDriver(Integer vehicleNumber, Integer driverVehiclenumber) {

        Cab cab=this.cabRepository.findByVehicleNumber(vehicleNumber);
        Driver driver=this.driverRepository.findByDriverVehicleNumber(driverVehiclenumber);
        cab.setDriver(driver);
        return this.cabRepository.save(cab);




    }

    @Override
    public List<Cab> availableCabs(String pickupPoint, String dropPoint) {

        System.out.println(this.cabRepository.findByPickUpPointAndDropPoint(pickupPoint,dropPoint));
        return this.cabRepository.findByPickUpPointAndDropPoint(pickupPoint,dropPoint);

    }
}
