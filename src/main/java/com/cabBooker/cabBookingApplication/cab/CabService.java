package com.cabBooker.cabBookingApplication.cab;

import java.util.List;
import java.util.Optional;

public interface CabService {
    //public Cab registerNewCab(Cab newcab) throws CabException;

    Cab updateCab(Integer cabId,Cab updatedcab) throws CabException;

    public List<Cab> displayAllCabs() throws displayAllCabException;

    public Cab mapCabAndDriver(Integer vehicleNumber, Integer driverVehiclenumber);

  // public  Cab updateCabById(Cab cab);

   public  Cab deleteCabById(Integer cabId) throws deleteCabByIdException;

    public List<Cab> displayAvailableCabs();

    Cab updateCabById(Integer cabId, String pickUpPoint, String dropPoint) throws updateCabByIdException;


    Cab registerNewCab(Cabdto newcab) throws CabException;


    Cab changecabAgency(Integer cabId, String cabAgency);

    Cab getCabById(Integer cabId) throws getCabByIdException;


    List<Cab> getCabByAgencyName(String agencyname) throws CabException;

    Cab getCabByVehicleNumber(Integer vehicleNumber) throws CabException;

    Cab changeCabintonewCabAgency(Integer cabId,Integer cabAgencyId,Integer oldCabAgencyId) ;
}
