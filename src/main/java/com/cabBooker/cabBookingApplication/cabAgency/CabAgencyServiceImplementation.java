package com.cabBooker.cabBookingApplication.cabAgency;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.booking.BookingRepository;
import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabRepository;
import com.cabBooker.cabBookingApplication.driver.Driver;
import com.cabBooker.cabBookingApplication.driver.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CabAgencyServiceImplementation implements CabAgencySerivce{
    
    @Autowired
    private BookingRepository bookingRepository;
    

    @Autowired
    private CabAgencyRepository cabAgencyRepository;
    @Autowired
    private CabRepository cabRepository;

    @Autowired
    private DriverRepository driverRepository;
    @Override
    public CabAgency registerNewCabAgency(CabAgency newcabAgency) {
        return this.cabAgencyRepository.save(newcabAgency);
    }



    @Override
    public List<CabAgency>displayCabAgency() {
        return this.cabAgencyRepository.findAll();

    }

    @Override
    public CabAgency mapCabAgencyAndCab(String agencyname) {

        CabAgency cabAgency= this.cabAgencyRepository.findByCabAgencyName(agencyname);
        List<Cab>cabs=this.cabRepository.findByAgencyName(agencyname);
        List<Driver>drivers=this.driverRepository.findByDriverAgencyName(agencyname);
        cabAgency.setCabs(cabs);
        cabAgency.setDrivers(drivers);

        return this.cabAgencyRepository.save(cabAgency);
    }


}
