package com.cabBooker.cabBookingApplication.cabAgency;

import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabRepository;
import com.cabBooker.cabBookingApplication.driver.Driver;
import com.cabBooker.cabBookingApplication.driver.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CabAgencyServiceImplementation implements CabAgencySerivce {

    @Autowired
    private CabAgencyRepository cabAgencyRepository;
    @Autowired
    private CabRepository cabRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public CabAgency registerNewCabAgency(CabAgency newcabAgency) throws CabAgencyMissingInputFieldException {

        if (newcabAgency.getCabAgencyId() == null || newcabAgency.getCabAgencyName() == null
                || newcabAgency.getCabAgencyEmail() == null || newcabAgency.getCabAgencyPassword() == null
                || newcabAgency.getCabAgencyMobileNumber() == null)
            throw new CabAgencyMissingInputFieldException("Cab Agency is null");
        return this.cabAgencyRepository.save(newcabAgency);
    }


    @Override
    public List<CabAgency> displayCabAgency() {
        return this.cabAgencyRepository.findAll();

    }

    @Override
    public CabAgency mapCabAgencyAndCab(String agencyname) throws CabAgencyNotFoundException {

        CabAgency cabAgency = this.cabAgencyRepository.findByCabAgencyName(agencyname);
        if (cabAgency == null) throw new CabAgencyNotFoundException("");
        List<Cab> cabs = this.cabRepository.findByAgencyName(agencyname);
        List<Driver> drivers = this.driverRepository.findByDriverAgencyName(agencyname);
        cabAgency.setCabs(cabs);
        cabAgency.setDrivers(drivers);

        return this.cabAgencyRepository.save(cabAgency);
    }

    @Override
    public CabAgency mapCabAgencyAndCabUsingId(Integer cabAgencyId) throws CabAgencyNotFoundException {

        Optional<CabAgency> cabagency = this.cabAgencyRepository.findById(cabAgencyId);
        List<Cab> cabs = this.cabRepository.findAllById(Collections.singleton(cabAgencyId));

        if (cabagency.isPresent()) {
            CabAgency cabAgency = cabagency.get();
            cabAgency.setCabs(cabs);
            return cabagency.get();
        } else throw new CabAgencyNotFoundException("");


    }

    @Override
    public CabAgency mapCabAgencyAndDriversUsingId(Integer cabAgencyId) throws CabAgencyNotFoundException {
        Optional<CabAgency> cabagency = this.cabAgencyRepository.findById(cabAgencyId);
        List<Driver> drivers = this.driverRepository.findAllById(Collections.singleton(cabAgencyId));

        if (cabagency.isPresent()) {
            CabAgency cabAgency = cabagency.get();
            cabAgency.setDrivers(drivers);
            return cabagency.get();
        } else throw new CabAgencyNotFoundException("");
    }

    @Override
    public CabAgency updateCabPickUpLocationInCabAgency(Integer cabAgencyId, Integer cabId, String fromLocation) {

        Optional<CabAgency>cabAgency= this.cabAgencyRepository.findById(cabAgencyId);
        Optional<Cab>cab=this.cabRepository.findById(cabId);

        CabAgency cabAgencyFound= cabAgency.get();
        Cab cabfound=cab.get();

        cabfound.setPickUpPoint(fromLocation);
        this.cabRepository.save(cabfound);
        return this.cabAgencyRepository.save(cabAgencyFound);


    }


    @Override
    public CabAgency updateCabAgencyMobileNumberById(Integer agencyId, Long mobileNumber) throws CabAgencyNotFoundException {

        Optional<CabAgency>findCabAgency= this.cabAgencyRepository.findById(agencyId);

        if(findCabAgency.isPresent()) {
            CabAgency cabAgencyFound = findCabAgency.get();
            cabAgencyFound.setCabAgencyMobileNumber(mobileNumber);
            return this.cabAgencyRepository.save(cabAgencyFound);
        }
        else throw new CabAgencyNotFoundException("No such cab agency found");
    }

    @Override
    public CabAgency updateCabAgencyPasswordById(Integer agencyId, String newPassword) throws CabAgencyNotFoundException{
        Optional<CabAgency>findCabAgency= this.cabAgencyRepository.findById(agencyId);
        if(findCabAgency.isPresent()) {
            CabAgency cabAgencyFound = findCabAgency.get();
            cabAgencyFound.setCabAgencyPassword(newPassword);
            return this.cabAgencyRepository.save(cabAgencyFound);
        }
        else throw new CabAgencyNotFoundException("No such cab agency found");

    }

    @Override
    public CabAgency deleteCabAgencyById(Integer cabAgencyId) throws CabAgencyNotFoundException {
        Optional<CabAgency>findCabAgency= this.cabAgencyRepository.findById(cabAgencyId);
        if(findCabAgency.isPresent()) {
            CabAgency cabAgencyFound = findCabAgency.get();
            cabAgencyFound.getCabs().stream().forEach(c->c.setAgencyName(null));
            cabAgencyFound.getDrivers().stream().forEach(d->d.setDriverAgencyName(null));
//                    nameList.stream().map((s)->s.toUpperCase()).forEach((s)->System.out.println(s));
            this.cabAgencyRepository.deleteById(cabAgencyId);
            return findCabAgency.get();
        }
        else throw new CabAgencyNotFoundException("No such cab agency found");

    }
}

