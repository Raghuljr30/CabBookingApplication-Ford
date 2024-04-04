package com.cabBooker.cabBookingApplication.cab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins= "http://localhost:4200")
@RestController
public class CabController {

    @Autowired
    private CabService cabService;


    /** New Cab Registration*/
    @PostMapping("cab")
    public Cab registerNewCab(@RequestBody Cabdto newcab) throws CabException {
       return this.cabService.registerNewCab(newcab);
    }

   @PutMapping("cab/{cabId}")
    public Cab updateCab(@PathVariable("cabId") Integer cabId,@RequestBody Cab updatedcab)throws CabException
    {
        return this.cabService.updateCab(cabId,updatedcab);
    }


    /** Mapping cab and respective Driver  */

    @PatchMapping("/cab-driver/{vehiclenumber}/{drivervehiclenumber}")
    public Cab mapCabAndDriver(@PathVariable("vehiclenumber")Integer vehicleNumber,
                               @PathVariable("drivervehiclenumber")Integer drivervehiclenumber)
    {
        return this.cabService.mapCabAndDriver(vehicleNumber,drivervehiclenumber);
    }
    @GetMapping("/cabs/displayall")
    public List<Cab> displayAllCabs() throws displayAllCabException{
        return this.cabService.displayAllCabs();

    }
    @PatchMapping("/cab/{cabId}/{pickUpPoint}/{dropPoint}")
    public Cab updateCabById(@PathVariable("cabId") Integer cabId,@PathVariable("pickUpPoint") String pickUpPoint ,@PathVariable("dropPoint") String dropPoint)throws updateCabByIdException {

        return this.cabService.updateCabById(cabId,pickUpPoint,dropPoint);
    }

    @DeleteMapping("/cab/{cabId}")
    public  Cab deleteCabById(@PathVariable Integer cabId) throws deleteCabByIdException{
        return this.cabService.deleteCabById(cabId);
    }



    @GetMapping("/display/Cabs")
    public List<Cab> displayAvailableCabs() {

        return  this.cabService.displayAvailableCabs();
    }
    @PatchMapping("cab/{cabId}/{cabAgency}")
    public Cab changecabAgency(@PathVariable("cabId") Integer cabId,@PathVariable("cabAgency") String cabAgency){
        return this.cabService.changecabAgency(cabId,cabAgency);

    }
    @GetMapping("cab/cabId/{cabId}")
    public  Cab getCabById(@PathVariable Integer cabId) throws getCabByIdException {
        return this.cabService.getCabById(cabId);
    }
    @GetMapping("cab/agencyname/{agencyname}")
    public List<Cab> getCabByAgencyName(@PathVariable String agencyname) throws CabException{
        return this.cabService.getCabByAgencyName(agencyname);
    }
    @GetMapping("cab/vehicleNumber/{vehicleNumber}")
    public Cab getCabByVehicleNumber(@PathVariable Integer vehicleNumber) throws CabException{
        return this.cabService.getCabByVehicleNumber(vehicleNumber);
    }

    @PatchMapping("cabChangeAgency/{cabId}/{newCabAgencyId}/{oldCabAgencyId}")
    public Cab changeCabintonewCabAgency(@PathVariable("cabId")Integer cabId,@PathVariable("newCabAgencyId")Integer newCabAgencyId,
                                         @PathVariable("oldCabAgencyId")Integer oldCabAgencyId)
    {
        return  this.cabService.changeCabintonewCabAgency(cabId,newCabAgencyId,oldCabAgencyId);
    }
}

