package com.cabBooker.cabBookingApplication.cabAgency;


import com.cabBooker.cabBookingApplication.Authentication.CabAgencyNotAuthenticatedException;
import com.cabBooker.cabBookingApplication.Authentication.JwtTokenService;
import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.cab.Cab;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:4200"})
@RestController
public class CabAgencyController {

    @Autowired
    private CabAgencySerivce cabAgencySerivce;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping("/cabagency")
    public CabAgency registerCabAgency(@Valid @RequestBody CabAgencyDto newCabAgency) throws CabAgencyMissingInputFieldException,CabAgencyCreationException ,AccountAlreadyExistException,CabAgencyNotAuthenticatedException{

        return this.cabAgencySerivce.registerNewCabAgency(newCabAgency);
    }

    @PostMapping("/cabagency/login")
    public Boolean loginCabAgency(@Valid @RequestBody CabAgencyLoginDto cabAgencyLogin, HttpServletResponse response) throws CabAgencyNotFoundException {

        return this.cabAgencySerivce.loginCabAgency(cabAgencyLogin,response);
    }

    @GetMapping("/cabagencies")
    public List<CabAgency> displayAllCabAgencies(HttpServletRequest request) throws CabAgencyNotAuthenticatedException
    {
        return this.cabAgencySerivce.displayCabAgency(request);
    }



    @GetMapping("/cabagency/{agencyId}")
    public CabAgency displayCabAgencyById(@PathVariable("agencyId")Integer cabAgencyId,HttpServletRequest request) throws CabAgencyNotFoundException, CabAgencyNotAuthenticatedException {
        return this.cabAgencySerivce.displayCabAgencyById(cabAgencyId,request);
    }


    @PatchMapping("/cabagency/cabs/{agencyId}")
    public CabAgency mapCabAgencyAndCabsUsingId(@PathVariable("agencyId")Integer agencyId) throws CabAgencyNotFoundException{
        return this.cabAgencySerivce.mapCabAgencyAndCabUsingId(agencyId);
    }

    @PatchMapping("/cabagency/drivers/{agencyId}")
    public CabAgency mapCabAgencyAndDriversUsingId(@PathVariable("agencyId")Integer agencyId) throws CabAgencyNotFoundException{
        return this.cabAgencySerivce.mapCabAgencyAndDriversUsingId(agencyId);
    }


    @PatchMapping("/cabagency/cablocation/{cabAgencyId}/{cabId}/{fromLocation}/{toLocation}/{cabFare}")
    public CabAgency updateCabPickUpLocationInCabAgency(@PathVariable("cabAgencyId") Integer cabAgencyId,
                                                  @PathVariable("cabId")Integer cabId,
                                                  @PathVariable("fromLocation") String fromLocation,
                                                        @PathVariable("toLocation") String toLocation,@PathVariable("cabFare")Integer cabFare)
            throws CabNotFoundInCabAgencyException, CabAgencyNotFoundException,CabAgencyMissingInputFieldException
    {
       return this.cabAgencySerivce.updateCabPickUpLocationAndDropLocationInCabAgency(cabAgencyId,cabId,fromLocation,toLocation,cabFare);
    }

    @PatchMapping ("/cabagency/mobile/{id}")
    public CabAgency updateCabAgencyMobileNumberById(@PathVariable("id") Integer agencyId,
                                                @RequestBody CabAgencyMobileNumberDto cabAgencyMobileNumberDto)
            throws CabAgencyNotFoundException, CabAgencyMissingInputFieldException
    {
        return this.cabAgencySerivce.updateCabAgencyMobileNumberById(agencyId,cabAgencyMobileNumberDto);
    }

    @PatchMapping("/cabagency/password/{id}")
    public CabAgency updateCabAgencyPasswordById(@PathVariable("id") Integer agencyId,
                                              @RequestBody CabAgencyPasswordDto cabAgencyPasswordDto)
    throws  CabAgencyNotFoundException,CabAgencyMissingInputFieldException
    {
        return this.cabAgencySerivce.updateCabAgencyPasswordById(agencyId,cabAgencyPasswordDto);
    }



    @DeleteMapping("/{cabAgencyId}")
    public CabAgency deleteCabAgencyById(@PathVariable("cabAgencyId") Integer cabAgencyId) throws CabAgencyNotFoundException{

        return this.cabAgencySerivce.deleteCabAgencyById(cabAgencyId);
    }


    //additional functionalities
    @GetMapping("/cabagency/cabs/{cabAgencyId}")
    public List<Cab> filterCabsByCabAgencyId(@PathVariable("cabAgencyId") Integer cabAgencyId)
    {   System.out.println(this.cabAgencySerivce.filterCabsByCabAgencyId(cabAgencyId));
        return this.cabAgencySerivce.filterCabsByCabAgencyId(cabAgencyId);
    }

    @GetMapping("/cabagency/booked-cabs/{cabAgencyId}")
    public List<Cab> displayAllBookedCabsOfCabAgency(@PathVariable("cabAgencyId") Integer cabAgencyId)
    {
        return this.cabAgencySerivce.displayAllBookedCabsOfCabAgency(cabAgencyId);
    }


    @GetMapping("/cabagency/unbooked-cabs/{cabAgencyId}")
    public List<Cab> displayAllUnBookedCabsOfCabAgency(@PathVariable("cabAgencyId") Integer cabAgencyId,HttpServletRequest request) throws CabAgencyNotAuthenticatedException
    {
        return this.cabAgencySerivce.displayAllUnBookedCabsOfCabAgency(cabAgencyId,request);
    }


    @PostMapping("cabagency/logout")
    public String logout(HttpServletResponse response) throws CabAgencyNotAuthenticatedException{
        jwtTokenService.setJwtToken("");
        System.out.println(jwtTokenService.getJwtToken()+"logout -__-__-__-__--_-___-_-____");
        Cookie cookie = new Cookie("jwt","");
        response.addCookie(cookie);
        return "Logout Success !";
    }

    public void autheticate(HttpServletResponse request)
    {

    }







}
