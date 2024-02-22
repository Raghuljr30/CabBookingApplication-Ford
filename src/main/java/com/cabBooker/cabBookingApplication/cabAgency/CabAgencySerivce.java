package com.cabBooker.cabBookingApplication.cabAgency;

import com.cabBooker.cabBookingApplication.cab.Cab;

import java.util.List;

public interface CabAgencySerivce {

    public CabAgency registerNewCabAgency(CabAgency newCabAgency) throws CabAgencyMissingInputFieldException;
    public List<CabAgency> displayCabAgency();

    public  CabAgency mapCabAgencyAndCab(String agencyname) throws CabAgencyNotFoundException;

    //MAP CAB AGENCY AND CAB USING CAB AGENCY ID -TODO

    public CabAgency mapCabAgencyAndCabUsingId(Integer cabAgencyId) throws CabAgencyNotFoundException;

    public CabAgency mapCabAgencyAndDriversUsingId(Integer cabAgencyId) throws CabAgencyNotFoundException;

    // CAB AGENCY HAVE THE CONTROL TO UPDATE THE LOCATION AND FAIR OF THE CAB

    public CabAgency updateCabPickUpLocationInCabAgency(Integer cabAgencyId,Integer cabId,String fromLocation);

    public CabAgency deleteCabAgencyById(Integer cabAgencyId) throws CabAgencyNotFoundException;
    public CabAgency updateCabAgencyMobileNumberById(Integer agencyId,Long mobileNumber) throws CabAgencyNotFoundException;
    public CabAgency updateCabAgencyPasswordById(Integer agencyId,String newPassword) throws CabAgencyNotFoundException;



}
