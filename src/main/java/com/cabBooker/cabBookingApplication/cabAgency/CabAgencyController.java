package com.cabBooker.cabBookingApplication.cabAgency;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CabAgencyController {

    @Autowired
    private CabAgencySerivce cabAgencySerivce;


    /** cabAgency Registration*/
    @PostMapping("/cabagency")
    public CabAgency registerCabAgency(@RequestBody CabAgency cabAgency) throws CabAgencyMissingInputFieldException {

        return this.cabAgencySerivce.registerNewCabAgency(cabAgency);
    }

    @GetMapping("/cabagencies")
    public List<CabAgency> displayCabAgencies()
    {
        return this.cabAgencySerivce.displayCabAgency();
    }

    /** Mapping cabs to the respective cabAgencies*/
    @PatchMapping("/cabagency-cabs/{agencyname}")
    public CabAgency mapCabAgencyAndCabs(@PathVariable("agencyname")String agencyname) throws CabAgencyNotFoundException
    {
        return this.cabAgencySerivce.mapCabAgencyAndCab(agencyname);
    }


    @PatchMapping("/cabagency-cabs-byId/{agencyId}")
    public CabAgency mapCabAgencyAndCabsUsingId(@PathVariable("agencyId")Integer agencyId) throws CabAgencyNotFoundException {
        return this.cabAgencySerivce.mapCabAgencyAndCabUsingId(agencyId);
    }

    @PatchMapping("/cabagency-drivers-byId/{agencyId}")
    public CabAgency mapCabAgencyAndDriversUsingId(@PathVariable("agencyId")Integer agencyId) throws CabAgencyNotFoundException{
        return this.cabAgencySerivce.mapCabAgencyAndDriversUsingId(agencyId);
    }

    @PatchMapping("/cabagency-update-CabLocationById/{cabAgencyId}/{cabId}/{fromLocation}")
    public CabAgency updateCabPickUpLocationInCabAgency(@PathVariable("cabAgencyId") Integer cabAgencyId,
                                                  @PathVariable("cabId")Integer cabId,
                                                  @PathVariable("fromLocation") String fromLocation)
    {
       return this.cabAgencySerivce.updateCabPickUpLocationInCabAgency(cabAgencyId,cabId,fromLocation);
    }

    @PatchMapping ("/update/customerMobile/{id}/{newMobileNumber}")
    public CabAgency updateCabAgencyMobileNumberById(@PathVariable("id") Integer agencyId,
                                                @PathVariable("newMobileNumber")Long mobileNumber)
            throws CabAgencyNotFoundException
    {
        return this.cabAgencySerivce.updateCabAgencyMobileNumberById(agencyId,mobileNumber);
    }

    @PatchMapping("/update/cabAgencyPassword/{id}/{newPassword}")
    public CabAgency updateCabAgencyPasswordById(@PathVariable("id") Integer agencyId,
                                              @PathVariable("newPassword") String newPassword)
    throws  CabAgencyNotFoundException
    {
        return this.cabAgencySerivce.updateCabAgencyPasswordById(agencyId,newPassword);
    }



    @DeleteMapping("/delete/{cabAgencyId}")
    public CabAgency deleteCabAgencyById(@PathVariable("cabAgencyId") Integer cabAgencyId) throws CabAgencyNotFoundException {

        return this.cabAgencySerivce.deleteCabAgencyById(cabAgencyId);
    }




}
