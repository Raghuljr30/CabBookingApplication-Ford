package com.cabBooker.cabBookingApplication.cabAgency;


import com.cabBooker.cabBookingApplication.booking.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CabAgencyController {

    @Autowired
    private CabAgencySerivce cabAgencySerivce;


    /** cabAgency Registration*/
    @PostMapping("/cabagency")
    public CabAgency registerCabAgency(@RequestBody CabAgency cabAgency) throws CabAgencyMissingInputFieldException,CabAgencyCreationException {

        return this.cabAgencySerivce.registerNewCabAgency(cabAgency);
    }

    @PostMapping("/cabagency/login/{id}/{cabAgencyEmail}/{cabAgencyPassword}")
    public Boolean loginCabAgency(@PathVariable("id")Integer id,
                                  @PathVariable("cabAgencyEmail")String cabAgencyEmail,
                                  @PathVariable("cabAgencyPassword")String cabAgencyPassword)
    {
        return this.cabAgencySerivce.loginCabAgency(id,cabAgencyEmail,cabAgencyPassword);
    }

    @GetMapping("/cabagencies")
    public List<CabAgency> displayAllCabAgencies()
    {
        return this.cabAgencySerivce.displayCabAgency();
    }

    /** Mapping cabs to the respective cabAgencies*/
//    @PatchMapping("/cabagency-cabs/{agencyname}")
//    public CabAgency mapCabAgencyAndCabs(@PathVariable("agencyname")String agencyname) throws CabAgencyNotFoundException
//    {
//        return this.cabAgencySerivce.mapCabAgencyAndCab(agencyname);
//    }

    @GetMapping("/cabAgencies/{id}")
    public CabAgency displayCabAgencyById(@PathVariable("id")Integer cabAgencyId) throws CabAgencyNotFoundException {
        return this.cabAgencySerivce.displayCabAgencyById(cabAgencyId);
    }


    @PatchMapping("/cabagency-cabs-byId/{agencyId}")
    public CabAgency mapCabAgencyAndCabsUsingId(@PathVariable("agencyId")Integer agencyId) throws CabAgencyNotFoundException {
        return this.cabAgencySerivce.mapCabAgencyAndCabUsingId(agencyId);
    }

    @PatchMapping("/cabagency-drivers-byId/{agencyId}")
    public CabAgency mapCabAgencyAndDriversUsingId(@PathVariable("agencyId")Integer agencyId) throws CabAgencyNotFoundException{
        return this.cabAgencySerivce.mapCabAgencyAndDriversUsingId(agencyId);
    }


    @PatchMapping("/cabagency-update-CabLocationById/{cabAgencyId}/{cabId}/{fromLocation}/{toLocation}")
    public CabAgency updateCabPickUpLocationInCabAgency(@PathVariable("cabAgencyId") Integer cabAgencyId,
                                                  @PathVariable("cabId")Integer cabId,
                                                  @PathVariable("fromLocation") String fromLocation,
                                                        @PathVariable("toLocation") String toLocation)
            throws CabNotFoundInCabAgencyException, CabAgencyNotFoundException,CabAgencyMissingInputFieldException
    {
       return this.cabAgencySerivce.updateCabPickUpLocationAndDropLocationInCabAgency(cabAgencyId,cabId,fromLocation,toLocation);
    }

    @PatchMapping ("/update/customerMobile/{id}/{newMobileNumber}")
    public CabAgency updateCabAgencyMobileNumberById(@PathVariable("id") Integer agencyId,
                                                @PathVariable("newMobileNumber")Long mobileNumber)
            throws CabAgencyNotFoundException, CabAgencyMissingInputFieldException
    {
        return this.cabAgencySerivce.updateCabAgencyMobileNumberById(agencyId,mobileNumber);
    }

    @PatchMapping("/update/cabAgencyPassword/{id}/{newPassword}")
    public CabAgency updateCabAgencyPasswordById(@PathVariable("id") Integer agencyId,
                                              @PathVariable("newPassword") String newPassword)
    throws  CabAgencyNotFoundException,CabAgencyMissingInputFieldException
    {
        return this.cabAgencySerivce.updateCabAgencyPasswordById(agencyId,newPassword);
    }



    @DeleteMapping("/delete/{cabAgencyId}")
    public CabAgency deleteCabAgencyById(@PathVariable("cabAgencyId") Integer cabAgencyId) throws CabAgencyNotFoundException {

        return this.cabAgencySerivce.deleteCabAgencyById(cabAgencyId);
    }




}
