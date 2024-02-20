package com.cabBooker.cabBookingApplication.cabAgency;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CabAgencyController {

    @Autowired
    private CabAgencySerivce cabAgencySerivce;

    /** cabAgency Registration*/
    @PostMapping("/cabagency")
    public CabAgency registerCabAgency(@RequestBody CabAgency cabAgency)
    {
        return this.cabAgencySerivce.registerNewCabAgency(cabAgency);
    }

    /** Mapping cabs to the respective cabAgencies*/
    @PatchMapping("/cabagency-cab/{agencyname}")
    public CabAgency mapCabAgencyAndCab(@PathVariable("agencyname")String agencyname)
    {
        return this.cabAgencySerivce.mapCabAgencyAndCab(agencyname);
    }



}
