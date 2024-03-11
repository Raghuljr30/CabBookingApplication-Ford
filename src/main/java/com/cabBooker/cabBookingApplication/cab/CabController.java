package com.cabBooker.cabBookingApplication.cab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
public class CabController {

    @Autowired
    private CabService cabService;


    /** New Cab Registration*/
    @PostMapping("/cab")
    public Cab registerCab(@RequestBody Cab cab)
    {
        return this.cabService.registerNewCab(cab);
    }


    /** Mapping cab and respective Driver  */

    @PatchMapping("/cab-driver/{vehiclenumber}/{drivervehiclenumber}")
    public Cab mapCabAndDriver(@PathVariable("vehiclenumber")Integer vehicleNumber,
                               @PathVariable("drivervehiclenumber")Integer drivervehiclenumber)
    {
        return this.cabService.mapCabAndDriver(vehicleNumber,drivervehiclenumber);
    }

}
