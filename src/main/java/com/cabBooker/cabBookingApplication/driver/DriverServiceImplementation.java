package com.cabBooker.cabBookingApplication.driver;

import com.cabBooker.cabBookingApplication.cab.CabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class DriverServiceImplementation implements DriverService{

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private CabRepository cabRepository;

    @Override
    public Driver registerNewDriver(Driver driver) throws nullFieldException {
      if(driver.getDriverAgencyId()==null||driver.getDriverAgencyName()==null||driver.getDriverEmail()==null||driver.getDriverMobileNumber()==null || driver.getDriverVehicleNumber()==null)
      {
          throw new nullFieldException("Driver details can't be null");
      }
        return this.driverRepository.save(driver);
    }

    @Override
    public List<Driver> listAllDrivers() throws emptyListException{
        List<Driver> drivers = driverRepository.findAll();
        if(drivers==null)
        {
            throw new emptyListException("There are no drivers");
        }
        return this.driverRepository.findAll();
    }

   @Override
    public Optional<Driver> getDriverById(Integer driverId) throws nullFieldException,noChangeException
   {
       if(driverId == null)
       {
           throw new nullFieldException("DriverID can't be null");
       }
       Optional<Driver> driverOpt = driverRepository.findById(driverId);
       if(driverOpt.isEmpty())
       {
           throw new noChangeException("No such Id Exists: "+driverId);
       }

       return this.driverRepository.findById(driverId);
   }

    @Override
    public Driver deleteDriverById(Integer driverId) throws nullFieldException {


        if(driverId == null)
        {
            throw new nullFieldException("DriverID can't be null");
        }
            Optional<Driver> driverOpt = driverRepository.findById(driverId);
        if(driverOpt.isEmpty())
            throw new nullFieldException("No data found");
           Driver driver=driverOpt.get();
           this.cabRepository.deleteById(driver.getDriverId());
            this.driverRepository.deleteById(driver.getDriverId());
            return null;

    }

    @Override
    public Driver updateDriver(Integer driverId, Driver newDriver) throws nullFieldException {
        if(driverId == null)
        {
            throw new nullFieldException("DriverId can't be null");
        }
        Optional<Driver> driverOpt = driverRepository.findById(driverId);
        if(driverOpt.isEmpty())
        {
            throw new nullFieldException("No such Driver Exists: "+driverId);
        }
        Driver updateDriver = driverOpt.get();
        updateDriver.setDriverPassword(newDriver.getDriverPassword());
        updateDriver.setDriverAgencyName(newDriver.getDriverAgencyName());
        updateDriver.setDriverMobileNumber(newDriver.getDriverMobileNumber());
        updateDriver.setDriverId(newDriver.getDriverId());
        updateDriver.setDriverEmail(newDriver.getDriverEmail());
        updateDriver.setDriverVehicleNumber(newDriver.getDriverVehicleNumber());
        updateDriver.setLicenseNumber(newDriver.getLicenseNumber());
        updateDriver.setReviews(newDriver.getReviews());
        updateDriver.setDriverName((newDriver.getDriverName()));


            return this.driverRepository.save(updateDriver);

    }
    @Override
    public List<Driver> getDriversByAgencyName(String agencyName) throws emptyListException, nullFieldException {
        if(agencyName==null)
        {
            throw new nullFieldException("AgencyName can't be null");
        }
        List<Driver> drivers = driverRepository.findByDriverAgencyName(agencyName);
        if(drivers.isEmpty())
        {
            throw new emptyListException("No drivers are present in this agency");
        }
        return this.driverRepository.findByDriverAgencyName(agencyName);
    }

    @Override
    public Driver changeAgency(Integer driverId, String driverAgencyName,Integer driverAgencyId)throws noChangeException,nullFieldException
    {

        if(driverId==null)
            throw new nullFieldException("DriverID can't be null");
        Optional<Driver> driverOpt= this.driverRepository.findById(driverId);
       Driver driver = driverOpt.get();
       if((driver.getDriverAgencyId().equals(driverAgencyId))||(driver.getDriverAgencyName().equals(driverAgencyName)))
       {
           throw new noChangeException("No change detected");
       }
       driver.setDriverAgencyId(driverAgencyId);
       driver.setDriverAgencyName(driverAgencyName);
        return this.driverRepository.save(driver);

    }

    @Override
    public Driver changeMobileNumber(Integer driverId, Long driverMobileNumber)throws noChangeException,nullFieldException {

        if(driverId==null)
            throw new nullFieldException("DriverId can't be empty");
        if(driverMobileNumber==null)
            throw new nullFieldException("Driver's Mobile Number can't be empty");
        Optional<Driver> driverOpt= this.driverRepository.findById(driverId);
        Driver driver = driverOpt.get();

        if(driver.getDriverMobileNumber().equals(driverMobileNumber))
        {
            throw new noChangeException("No change detected");
        }
        driver.setDriverMobileNumber(driverMobileNumber);
        return this.driverRepository.save(driver);

          }

    @Override
    public Driver changePassword(Integer driverId, String password)throws noChangeException,nullFieldException {
        Optional<Driver> driverOpt= this.driverRepository.findById(driverId);
        Driver driver = driverOpt.get();
        if(password==null)
            throw new nullFieldException("New password can't be empty");
        if(driver.getDriverPassword().equals(password))
            throw new noChangeException("Enter new password");
        driver.setDriverPassword(password);
        return this.driverRepository.save(driver);

    }

    @Override
    public List<Driver> displayAllDriver() {

        return this.driverRepository.findAll();

    }
}




//    @Override
//    public Driver changeAgencyId(Integer driverAgencyId, Integer cabAgencyId,Cab newCabAgency) {
//
//        Optional<Driver> driverOpt = this.driverRepository.findById(driverAgencyId);
//        Optional<Cab> cabOpt = this.cabRepository.findById(cabAgencyId);
//        Driver driver = driverOpt.get();
//        Cab cab = cabOpt.get();
//        //driver.getDriverAgencyId().get
//        this.driverRepository.save(driver);
//        return cab.getCabAgencyId();
//
//    }
//
//    @Override
//    public Driver getDriverVehicleNumber(Driver driver) {
//        return this.driverRepository.findByDriverVehicleNumber(number);
//        return null;
//    }



