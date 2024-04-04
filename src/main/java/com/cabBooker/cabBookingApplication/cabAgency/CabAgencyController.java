package com.cabBooker.cabBookingApplication.cabAgency;


import com.cabBooker.cabBookingApplication.cab.Cab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CabAgencyController {

    @Autowired
    private CabAgencySerivce cabAgencySerivce;

    /** cabAgency Registration*/
    @PostMapping("/cabagency/register")
    public CabAgency registerCabAgency(@RequestBody CabAgency cabAgency)
    {
        return this.cabAgencySerivce.registerNewCabAgency(cabAgency);
    }

    /** Mapping cabs to the respective cabAgencies*/
    @PatchMapping("/cabagency/cab/{agencyname}")
    public CabAgency mapCabAgencyAndCab(@PathVariable("agencyname")String agencyname)
    {
        return this.cabAgencySerivce.mapCabAgencyAndCab(agencyname);
    }

    @PatchMapping("/cabagency/cabagencyid/cab/{agencyId}")
    public CabAgency mapCabAgencyIdAndCab(@PathVariable("agencyId") Integer agencyId){
        return this.cabAgencySerivce.mapCabAgencyIdAndCab(agencyId);
    }






}
