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
import java.util.Optional;

@Service

public class CabAgencyServiceImplementation implements CabAgencySerivce{
    

    

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

    @Override
    public CabAgency mapCabAgencyIdAndCab(Integer agencyId) {
//        CabAgency cabAgency=this.cabAgencyRepository.findById(agencyId).get();
//        List<Cab> cabList= (List<Cab>) this.cabRepository.findById(agencyId).get();
//        List<Driver> driverList=(List<Driver>)this.driverRepository.findById(agencyId).get();
//        cabAgency.setCabs(cabList);
//        cabAgency.setDrivers(driverList);
        Optional<CabAgency> optionalCabAgency=this.cabAgencyRepository.findById(agencyId);
        CabAgency cabAgencyExist=optionalCabAgency.get();
      List<Cab> cabList=(List<Cab>) this.cabRepository.findById(agencyId).get();

        cabAgencyExist.setCabs(cabList);


       return cabAgencyRepository.save(cabAgencyExist);

    }


}
