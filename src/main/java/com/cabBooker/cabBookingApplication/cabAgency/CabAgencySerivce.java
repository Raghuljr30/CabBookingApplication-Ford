package com.cabBooker.cabBookingApplication.cabAgency;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.cab.Cab;

import java.util.List;
import com.cabBooker.cabBookingApplication.cab.CabNotFoundException;

public interface CabAgencySerivce {

    public CabAgency registerNewCabAgency(CabAgency newCabAgency) throws CabAgencyMissingInputFieldException,CabAgencyCreationException;

    public Boolean loginCabAgency(Integer id,String cabAgencyEmail,String cabAgencyPassword);
    public List<CabAgency> displayCabAgency();

    public CabAgency displayCabAgencyById(Integer cabAgencyId) throws CabAgencyNotFoundException;
    public CabAgency mapCabAgencyAndCabUsingId(Integer cabAgencyId) throws CabAgencyNotFoundException;

    public CabAgency mapCabAgencyAndDriversUsingId(Integer cabAgencyId) throws CabAgencyNotFoundException;

    // CAB AGENCY HAVE THE CONTROL TO UPDATE THE LOCATION AND FAIR OF THE CAB

    public CabAgency updateCabPickUpLocationAndDropLocationInCabAgency(Integer cabAgencyId,Integer cabId,String fromLocation,String toLocation)
            throws CabAgencyNotFoundException, CabNotFoundInCabAgencyException, CabAgencyMissingInputFieldException ;

    public CabAgency deleteCabAgencyById(Integer cabAgencyId) throws CabAgencyNotFoundException;
    public CabAgency updateCabAgencyMobileNumberById(Integer agencyId,Long mobileNumber) throws CabAgencyNotFoundException,CabAgencyMissingInputFieldException;
    public CabAgency updateCabAgencyPasswordById(Integer agencyId,String newPassword) throws CabAgencyNotFoundException,CabAgencyMissingInputFieldException;



}
