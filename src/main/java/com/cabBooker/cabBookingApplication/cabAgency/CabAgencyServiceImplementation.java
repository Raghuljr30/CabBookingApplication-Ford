package com.cabBooker.cabBookingApplication.cabAgency;

import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabNotFoundException;
import com.cabBooker.cabBookingApplication.cab.CabRepository;
import com.cabBooker.cabBookingApplication.driver.Driver;
import com.cabBooker.cabBookingApplication.driver.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    public CabAgency registerNewCabAgency(CabAgency newcabAgency) throws CabAgencyMissingInputFieldException,CabAgencyCreationException {

        if(newcabAgency.getCabAgencyId()==null) throw new CabAgencyCreationException("");
        if (newcabAgency.getCabAgencyName() == null
                || newcabAgency.getCabAgencyEmail() == null || newcabAgency.getCabAgencyPassword() == null
                || newcabAgency.getCabAgencyMobileNumber() == null)
            throw new CabAgencyMissingInputFieldException("Cab Agency is null");
       return this.cabAgencyRepository.save(newcabAgency);
//        return newcabAgency;

    }

    @Override
    public Boolean loginCabAgency(Integer id, String cabAgencyEmail, String cabAgencyPassword) {
        CabAgency cabAgencyRegistered= cabAgencyRepository.findByCabAgencyIdAndCabAgencyEmailAndCabAgencyPassword(id,cabAgencyEmail,cabAgencyPassword);

        if(cabAgencyRegistered!=null)
        {
            return  true;
        }
        return  false;
    }


    @Override
    public List<CabAgency> displayCabAgency() {
        return this.cabAgencyRepository.findAll();

    }

    @Override
    public CabAgency displayCabAgencyById(Integer cabAgencyId)  throws CabAgencyNotFoundException{
        Optional<CabAgency>cabAgency=cabAgencyRepository.findById(cabAgencyId);
        if(!cabAgency.isPresent()) throw new CabAgencyNotFoundException("Cab Agency not found");
        CabAgency cabAgencyFound=cabAgency.get();
        return cabAgencyFound;

    }

//    @Override
//    public CabAgency mapCabAgencyAndCab(String agencyname) throws CabAgencyNotFoundException {
//        if(this.cabAgencyRepository.findByCabAgencyName(agencyname)==null) throw new CabAgencyNotFoundException("");
//        CabAgency cabAgency = this.cabAgencyRepository.findByCabAgencyName(agencyname);
//        if (cabAgency == null) throw new CabAgencyNotFoundException("");
//        List<Cab> cabs = this.cabRepository.findByAgencyName(agencyname);
//        List<Driver> drivers = this.driverRepository.findByDriverAgencyName(agencyname);
//        cabAgency.setCabs(cabs);
//        cabAgency.setDrivers(drivers);
//
//        return this.cabAgencyRepository.save(cabAgency);
//    }

    @Override
    public CabAgency mapCabAgencyAndCabUsingId(Integer cabAgencyId) throws CabAgencyNotFoundException {

        Optional<CabAgency> cabagency = this.cabAgencyRepository.findById(cabAgencyId);
        List<Cab> cabs = this.cabRepository.findByCabAgencyId(cabAgencyId);

        if (cabagency.isPresent()) {
            CabAgency cabAgency = cabagency.get();
            cabAgency.setCabs(cabs);
            cabAgencyRepository.save(cabAgency);
            return cabagency.get();
        } else throw new CabAgencyNotFoundException("");


    }

    @Override
    public CabAgency mapCabAgencyAndDriversUsingId(Integer cabAgencyId) throws CabAgencyNotFoundException {
        Optional<CabAgency> cabAgency = this.cabAgencyRepository.findById(cabAgencyId);
        List<Driver> drivers = this.driverRepository.findByDriverAgencyId(cabAgencyId);
        System.out.println(drivers);
        if (cabAgency.isPresent()) {
            CabAgency cabAgencyFound = cabAgency.get();
            cabAgencyFound.setDrivers(drivers);
            cabAgencyRepository.save(cabAgencyFound);
            return cabAgency.get();
        } else throw new CabAgencyNotFoundException("");
    }

    @Override
    public CabAgency updateCabPickUpLocationAndDropLocationInCabAgency(Integer cabAgencyId, Integer cabId, String fromLocation,String toLocation)
    throws CabAgencyNotFoundException , CabNotFoundInCabAgencyException,CabAgencyMissingInputFieldException{

        Optional<CabAgency>cabAgency= this.cabAgencyRepository.findById(cabAgencyId);
        Optional<Cab>cab=this.cabRepository.findById(cabId);
        if(!cab.isPresent())
        {
          throw new CabNotFoundInCabAgencyException("");
        }

        if(cabAgency.isPresent())
        {
            CabAgency cabAgencyFound= cabAgency.get();
            Cab cabfound=cab.get();
            if(fromLocation==null || toLocation==null ||
            fromLocation.equals(" ") || toLocation.equals(" "))
            {
                throw new CabAgencyMissingInputFieldException("");
            }

            cabfound.setPickUpPoint(fromLocation);
            cabfound.setDropPoint(toLocation);
            this.cabRepository.save(cabfound);
            return this.cabAgencyRepository.save(cabAgencyFound);
        }
        else throw new CabAgencyNotFoundException("");



    }


    @Override
    public CabAgency updateCabAgencyMobileNumberById(Integer agencyId, Long mobileNumber) throws CabAgencyNotFoundException {

        Optional<CabAgency>findCabAgency= this.cabAgencyRepository.findById(agencyId);

        if(findCabAgency.isPresent()) {
            CabAgency cabAgencyFound = findCabAgency.get();
            cabAgencyFound.setCabAgencyMobileNumber(mobileNumber);
            return this.cabAgencyRepository.save(cabAgencyFound);
        }
        else throw new CabAgencyNotFoundException("");
    }

    @Override
    public CabAgency updateCabAgencyPasswordById(Integer agencyId, String newPassword)
            throws CabAgencyNotFoundException, CabAgencyMissingInputFieldException{
        Optional<CabAgency>findCabAgency= this.cabAgencyRepository.findById(agencyId);
        if(findCabAgency.isPresent()) {
            if(newPassword==null || newPassword.equals(" "))
            {
                throw new CabAgencyMissingInputFieldException("");
            }
            CabAgency cabAgencyFound = findCabAgency.get();
            cabAgencyFound.setCabAgencyPassword(newPassword);
            return this.cabAgencyRepository.save(cabAgencyFound);
        }
        else throw new CabAgencyNotFoundException("No such cab agency found");

    }

    @Override
    public CabAgency deleteCabAgencyById(Integer cabAgencyId) throws CabAgencyNotFoundException {
        Optional<CabAgency>findCabAgency= this.cabAgencyRepository.findById(cabAgencyId);
        if(!findCabAgency.isPresent()) throw new CabAgencyNotFoundException("No such cab agency found");
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

