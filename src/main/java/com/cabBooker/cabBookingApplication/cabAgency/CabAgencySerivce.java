package com.cabBooker.cabBookingApplication.cabAgency;

import com.cabBooker.cabBookingApplication.Authentication.CabAgencyNotAuthenticatedException;
import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.cab.Cab;

import java.util.List;
import com.cabBooker.cabBookingApplication.cab.CabNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CabAgencySerivce {

    public CabAgency registerNewCabAgency(CabAgencyDto newCabAgency) throws CabAgencyMissingInputFieldException,CabAgencyCreationException,AccountAlreadyExistException,CabAgencyNotAuthenticatedException;

    public Boolean loginCabAgency(CabAgencyLoginDto cabAgencyLogin, HttpServletResponse response) throws CabAgencyNotFoundException ;
    public List<CabAgency> displayCabAgency(HttpServletRequest request) throws CabAgencyNotAuthenticatedException;

    public CabAgency displayCabAgencyById(Integer cabAgencyId,HttpServletRequest request) throws CabAgencyNotFoundException,CabAgencyNotAuthenticatedException;
    public CabAgency mapCabAgencyAndCabUsingId(Integer cabAgencyId) throws CabAgencyNotFoundException;

    public CabAgency mapCabAgencyAndDriversUsingId(Integer cabAgencyId) throws CabAgencyNotFoundException;

    // CAB AGENCY HAVE THE CONTROL TO UPDATE THE LOCATION AND FAIR OF THE CAB

    public CabAgency updateCabPickUpLocationAndDropLocationInCabAgency(Integer cabAgencyId,Integer cabId,String fromLocation,String toLocation,Integer cabFare)
            throws CabAgencyNotFoundException, CabNotFoundInCabAgencyException, CabAgencyMissingInputFieldException;

    public CabAgency deleteCabAgencyById(Integer cabAgencyId) throws CabAgencyNotFoundException;
    public CabAgency updateCabAgencyMobileNumberById(Integer agencyId,CabAgencyMobileNumberDto cabAgencyMobileNumberDto) throws CabAgencyNotFoundException,CabAgencyMissingInputFieldException;
    public CabAgency updateCabAgencyPasswordById(Integer agencyId,CabAgencyPasswordDto cabAgencyPasswordDto) throws CabAgencyNotFoundException,CabAgencyMissingInputFieldException;

    public  List<Cab>filterCabsByCabAgencyId(Integer cabAgencyId) ;
    public List<Cab> displayAllBookedCabsOfCabAgency(Integer cabAgencyId) ;
    public List<Cab> displayAllUnBookedCabsOfCabAgency(Integer cabAgencyId,HttpServletRequest request) throws CabAgencyNotAuthenticatedException;





}
