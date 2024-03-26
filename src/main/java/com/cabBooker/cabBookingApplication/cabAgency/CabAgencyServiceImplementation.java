package com.cabBooker.cabBookingApplication.cabAgency;

import com.cabBooker.cabBookingApplication.Authentication.AuthControllerAdvice;
import com.cabBooker.cabBookingApplication.Authentication.CabAgencyAuthentication;
import com.cabBooker.cabBookingApplication.Authentication.CabAgencyNotAuthenticatedException;
import com.cabBooker.cabBookingApplication.Authentication.JwtTokenService;
import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabNotFoundException;
import com.cabBooker.cabBookingApplication.cab.CabRepository;
import com.cabBooker.cabBookingApplication.driver.Driver;
import com.cabBooker.cabBookingApplication.driver.DriverRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;


import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CabAgencyServiceImplementation implements CabAgencySerivce {

    @Autowired
    private CabAgencyRepository cabAgencyRepository;
    @Autowired
    private CabRepository cabRepository;
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CabAgencyAuthentication cabAgencyAuthentication;

    @Autowired
    private JwtTokenService jwtTokenService;

    /*************************************************************************************************************
     *Method                                        - registerCabAgency
     * Descriotion                                  - to register a new Cab Agency
     * @param newcabAgency                         - cabagency's registration details
     * @return CabAgency                          -  registered cab agency
     * @throws CabAgencyMissingInputFieldException - It is thrown if any of registration field is empty
     * Created By                                  - Raghul
     * Created Date                                 -

     *********************************************************************************************************************/

    @Override
    public CabAgency registerNewCabAgency(CabAgencyDto newcabAgency) throws CabAgencyMissingInputFieldException,CabAgencyCreationException,AccountAlreadyExistException{

        if(cabAgencyRepository.findByCabAgencyEmail(newcabAgency.getCabAgencyEmail())!=null) throw new AccountAlreadyExistException("Account with this Email already exist");
//        if(newcabAgency.getCabAgencyId()==null) throw new CabAgencyCreationException("");
        if (newcabAgency.getCabAgencyName() == null || newcabAgency.getCabAgencyEmail() == null || newcabAgency.getCabAgencyPassword() == null|| newcabAgency.getCabAgencyMobileNumber() == null)
            throw new CabAgencyMissingInputFieldException("Cab Agency is null");

        CabAgency saveNewCabAgency=new CabAgency();
        saveNewCabAgency.setCabAgencyName(newcabAgency.getCabAgencyName());
        saveNewCabAgency.setCabAgencyEmail(newcabAgency.getCabAgencyEmail());
        saveNewCabAgency.setCabAgencyMobileNumber(newcabAgency.getCabAgencyMobileNumber());
        saveNewCabAgency.setCabAgencyPassword(newcabAgency.getCabAgencyPassword());
        return this.cabAgencyRepository.save(saveNewCabAgency);

    }


    /*************************************************************************************************************
     *Method                                        - to login cab agency
     * Descriotion                                  - to register a new Cab Agency
     * @param cabAgencyLogin                       - Cab Agency owner submits cabagency's Login Credentials
     * @return Boolean                              -  true, if the cab agency exist with the given credentials otherwise false
     * Created By                                  - Raghul
     * Created Date                                 -

     *********************************************************************************************************************/


    @Override
    public Boolean loginCabAgency(CabAgencyLoginDto cabAgencyLogin, HttpServletResponse response) throws CabAgencyNotFoundException {
        CabAgency cabAgencyRegistered= cabAgencyRepository.findByCabAgencyIdAndCabAgencyEmailAndCabAgencyPassword(cabAgencyLogin.getCabAgencyId(),cabAgencyLogin.getCabAgencyEmail(),cabAgencyLogin.getCabAgencyPassword());
//        if(cabAgencyRegistered==null) throw  new CabAgencyNotFoundException("");
        if(cabAgencyRegistered!=null)
        {
            String issuer = cabAgencyLogin.getCabAgencyEmail();
            Date expiry= new Date(System.currentTimeMillis() + (1000 * 60 * 60 ));
            String jwt = Jwts.builder().setIssuer(issuer).setExpiration(expiry)
                    .signWith(SignatureAlgorithm.HS256,"secretKey").compact();

            jwtTokenService.setJwtToken(jwt);

            System.out.println(jwtTokenService.getJwtToken() +" login get token---------______------____");
            Cookie cookie = new Cookie("jwt",jwt);
            cookie.setPath("/cabagency"); // Set the cookie path

            cookie.setHttpOnly(true);
            response.addCookie(cookie);



            return  true;
        }

            return false;


    }
    /*************************************************************************************************************
     *Method                                        - displayCabAgency
     * Descriotion                                  - to display all cab agencies
     * @return List<CabAgency>                       -returns all the registered cab agencies
     * Created By                                  - Raghul
     * Created Date                                 -

     *********************************************************************************************************************/


    @Override
    public List<CabAgency> displayCabAgency(HttpServletRequest request)   throws CabAgencyNotAuthenticatedException{


        System.out.println(cabAgencyAuthentication.authenticate(jwtTokenService.getJwtToken())+"_----___----____----__----__--");
        if(cabAgencyAuthentication.authenticate(jwtTokenService.getJwtToken())==null)   throw new CabAgencyNotAuthenticatedException("UnAuthenticated");

        return this.cabAgencyRepository.findAll();

    }

    /*************************************************************************************************************
     *Method                                        - displayCabAgencyById
     * Descriotion                                  - to find and display the cab agency when provided with an id
     * @param cabAgencyId                                      - Cab agency's Id
     * @return CabAgency                            -returns the cab agency if found with the id
     * @throws  CabAgencyNotFoundException          -this error is thrown if there no cab agency exist with the given Id
     * Created By                                  - Raghul
     * Created Date                                 -

     *********************************************************************************************************************/



    @Override
    public CabAgency displayCabAgencyById(Integer cabAgencyId,HttpServletRequest request)  throws CabAgencyNotFoundException,CabAgencyNotAuthenticatedException{

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("jwt")) {
                    try {
                        cabAgencyAuthentication.authenticate(cookie.getValue());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
                else
                {
                    throw new CabAgencyNotAuthenticatedException("UnAuthenticated");
                }
            }
        }

        Optional<CabAgency>cabAgency=cabAgencyRepository.findById(cabAgencyId);
        if(!cabAgency.isPresent()) throw new CabAgencyNotFoundException("Cab Agency not found");
        CabAgency cabAgencyFound=cabAgency.get();
        return cabAgencyFound;

    }


    /*************************************************************************************************************
     *Method                                        - mapCabAgencyAndCabUsingId
     * Descriotion                                  - maps all cabs belonging to a cab agency
     * @param cabAgencyId                            - Cab agency's id
     * @return CabAgency                            -returns the cab agency with its associated cabs
     * @throws  CabAgencyNotFoundException          -this error is thrown if no cab agency exist with the given Id
     * Created By                                  - Raghul
     * Created Date                                 -

     *********************************************************************************************************************/

    @Override
    public CabAgency mapCabAgencyAndCabUsingId(Integer cabAgencyId) throws CabAgencyNotFoundException{

        Optional<CabAgency> cabagency = this.cabAgencyRepository.findById(cabAgencyId);
        List<Cab> cabs = this.cabRepository.findByCabAgencyId(cabAgencyId);

        if (cabagency.isPresent()) {
            CabAgency cabAgency = cabagency.get();
            cabAgency.setCabs(cabs);
            cabAgencyRepository.save(cabAgency);
            return cabagency.get();
        } else throw new CabAgencyNotFoundException("");


    }


    /*************************************************************************************************************
     *Method                                        - mapCabAgencyAndDriversUsingId
     * Descriotion                                  - maps all drivers belonging to a cab agency
     * @param cabAgencyId                            - Cab agency's id
     * @return CabAgency                            -returns the cab agency with its associated drivers
     * @throws  CabAgencyNotFoundException          -this error is thrown if  no cab agency exist with the given Id
     * Created By                                  - Raghul
     * Created Date                                 -

     *********************************************************************************************************************/


    @Override
    public CabAgency mapCabAgencyAndDriversUsingId(Integer cabAgencyId) throws CabAgencyNotFoundException {
        Optional<CabAgency> cabAgency = this.cabAgencyRepository.findById(cabAgencyId);
        List<Driver> drivers = this.driverRepository.findByDriverAgencyId(cabAgencyId);
        if (cabAgency.isPresent()) {
            CabAgency cabAgencyFound = cabAgency.get();
            cabAgencyFound.setDrivers(drivers);
            cabAgencyRepository.save(cabAgencyFound);
            return cabAgency.get();
        } else throw new CabAgencyNotFoundException("");
    }



    /*************************************************************************************************************
     *Method                                        - updateCabPickUpLocationAndDropLocationInCabAgency
     * Descriotion                                  - to update the cab pickup location and drop location with a new pickup and drop location
     * @param cabAgencyId                            - Cab agency's id
     * @param  cabId                                - Cab's id
     * @param  fromLocation                          - new pickup point for the cab
     * @param toLocation                             - new drop point for the cab
     * @return CabAgency                            -returns the cab agency with the newly updated pickup and drop location
     * @throws  CabAgencyNotFoundException          -this  is thrown if  no cab agency exist with the given Id
     * @throws  CabNotFoundInCabAgencyException     -this is thrown if no cab exist with the given cab id in the cabagency
     * @throws  CabAgencyMissingInputFieldException -this is thrown if any of the field in the cab agency is missing
     * Created By                                  - Raghul
     * Created Date                                 -

     *********************************************************************************************************************/

    @Override
    public CabAgency updateCabPickUpLocationAndDropLocationInCabAgency(Integer cabAgencyId, Integer cabId, String fromLocation,String toLocation,Integer cabFare)
    throws CabAgencyNotFoundException , CabNotFoundInCabAgencyException,CabAgencyMissingInputFieldException{

        Optional<CabAgency>cabAgency= this.cabAgencyRepository.findById(cabAgencyId);
        Optional<Cab>cab=this.cabRepository.findById(cabId);

        if(!cab.isPresent())
        {
            throw new CabNotFoundInCabAgencyException("");
        }
        if(!cab.get().getCabAgencyId().equals(cabAgency.get().getCabAgencyId()))
        {
            System.out.println(cab.get().getCabAgencyId()+" "+cabAgency.get().getCabAgencyId());
            throw new CabNotFoundInCabAgencyException("");
        }
        if(!cabAgency.isPresent()) throw new CabAgencyNotFoundException("");
        if(cabAgency.isPresent())
        {
            CabAgency cabAgencyFound= cabAgency.get();
            Cab cabfound=cab.get();
            if(fromLocation==null || toLocation==null ||
            fromLocation.equals(" ") || toLocation.equals(" "))
            {
                throw new CabAgencyMissingInputFieldException("");
            }

            cabfound.setPickUpPoint(fromLocation);
            cabfound.setDropPoint(toLocation);
            cabfound.setFair(Double.valueOf(cabFare));
            this.cabRepository.save(cabfound);
            return this.cabAgencyRepository.save(cabAgencyFound);
        }
        else throw new CabAgencyNotFoundException("");



    }

    /*************************************************************************************************************
     *Method                                        -updateCabAgencyMobileNumberById
     * Descriotion                                  - to update the cab agnency mobile number
     * @param cabAgencyId                            - Cab agency's id
     * @param cabAgencyMobileNumberDto               - new mobile number to be updated
     * @return CabAgency                            -returns the cab agency with update mobile number
     * @throws  CabAgencyNotFoundException          -this error is thrown if  no cab agency exist with the given Id
     * Created By                                  - Raghul
     * Created Date                                 -

     *********************************************************************************************************************/


    @Override
    public CabAgency updateCabAgencyMobileNumberById(Integer cabAgencyId,CabAgencyMobileNumberDto cabAgencyMobileNumberDto) throws CabAgencyNotFoundException {

        Optional<CabAgency>findCabAgency= this.cabAgencyRepository.findById(cabAgencyId);

        if(findCabAgency.isPresent()) {
            CabAgency cabAgencyFound = findCabAgency.get();
            cabAgencyFound.setCabAgencyMobileNumber(cabAgencyMobileNumberDto.getNewMobileNumber());
            return this.cabAgencyRepository.save(cabAgencyFound);
        }
        else throw new CabAgencyNotFoundException("");
    }


    /*************************************************************************************************************
     *Method                                        -updateCabAgencyPasswordById
     * Descriotion                                  - to update the cab agnency's password
     * @param cabAgencyId                            - Cab agency's id
     * @param cabAgencyPasswordDto                  - new password to be updated
     * @return CabAgency                            -returns the cab agency with updated password
     * @throws  CabAgencyNotFoundException          -this error is thrown if  no cab agency exist with the given Id
     * Created By                                  - Raghul
     * Created Date                                 -

     *********************************************************************************************************************/



    @Override
    public CabAgency updateCabAgencyPasswordById(Integer cabAgencyId,CabAgencyPasswordDto cabAgencyPasswordDto)
            throws CabAgencyNotFoundException, CabAgencyMissingInputFieldException{
        Optional<CabAgency>findCabAgency= this.cabAgencyRepository.findById(cabAgencyId);
        if(findCabAgency.isPresent()) {
            if(cabAgencyPasswordDto.getNewPassword()==null || cabAgencyPasswordDto.getNewPassword().equals(" "))
            {
                throw new CabAgencyMissingInputFieldException("");
            }
            CabAgency cabAgencyFound = findCabAgency.get();
            cabAgencyFound.setCabAgencyPassword(cabAgencyPasswordDto.getNewPassword());
            return this.cabAgencyRepository.save(cabAgencyFound);
        }
        else throw new CabAgencyNotFoundException("No such cab agency found");

    }

    /*************************************************************************************************************
     *Method                                        -filterCabsByCabAgencyId
     * Descriotion                                  - to filter the cabs based on the cab agency id
     * @param cabAgencyId                            - Cab agency's id
     * @return List<Cab>                            -returns the list of cab that are under a particular cab agency id
     * Created By                                  - Raghul
     * Created Date                                 -

     *********************************************************************************************************************/


    @Override
    public List<Cab> filterCabsByCabAgencyId(Integer cabAgencyId) {

        return this.cabRepository.findCabByCabAgencyId(cabAgencyId);
    }


    /*************************************************************************************************************
     *Method                                        -displayAllBookedCabsOfCabAgency
     * Descriotion                                  - to display all the cabs that got booked in the cab agency by a customer
     * @param cabAgencyId                            - Cab agency's id
     * @return List<Cab>                            -returns the list of cab that got booked  under a particular cab agency id
     * Created By                                  - Raghul
     * Created Date                                 -

     *********************************************************************************************************************/


    @Override
    public List<Cab> displayAllBookedCabsOfCabAgency(Integer cabAgencyId) {

        return cabRepository.findByCabAgencyIdAndAvailability(cabAgencyId,false);
    }

    /*************************************************************************************************************
     *Method                                        -displayAllUnBookedCabsOfCabAgency
     * Descriotion                                  - to display all the cabs that are still unbooked in the cab agency
     * @param cabAgencyId                            - Cab agency's id
     * @return List<Cab>                            -returns the list of cab that are unbooked under a particular cab agency id
     * Created By                                  - Raghul
     * Created Date                                 -

     *********************************************************************************************************************/

    @Override
    public List<Cab> displayAllUnBookedCabsOfCabAgency(Integer cabAgencyId,HttpServletRequest request)  throws CabAgencyNotAuthenticatedException {

        System.out.println(cabAgencyAuthentication.authenticate(jwtTokenService.getJwtToken())+"_----___----____----__----__--");
        if(cabAgencyAuthentication.authenticate(jwtTokenService.getJwtToken())==null)   throw new CabAgencyNotAuthenticatedException("UnAuthenticated");

        return cabRepository.findByCabAgencyIdAndAvailability(cabAgencyId,true);

    }

    /*************************************************************************************************************
     *Method                                        -deleteCabAgencyById
     * Descriotion                                  -to delete a particular cab agency with its id
     * @param cabAgencyId                           -Cab agency's id which need to be deleted
     * @return CabAgency                            -return the cab agency that got deleted
     * @throws CabAgencyNotFoundException           -this error is thrown if  no cab agency exist with the given Id
     * Created By                                  - Raghul
     * Created Date                                -

     *********************************************************************************************************************/



    @Override
    public CabAgency deleteCabAgencyById(Integer cabAgencyId) throws CabAgencyNotFoundException {
        Optional<CabAgency>findCabAgency= this.cabAgencyRepository.findById(cabAgencyId);
        if(!findCabAgency.isPresent()) throw new CabAgencyNotFoundException("No such cab agency found");
        if(findCabAgency.isPresent()) {
            CabAgency cabAgencyFound = findCabAgency.get();
            cabAgencyFound.getCabs().stream().forEach(c->c.setAgencyName(null));
            cabAgencyFound.getDrivers().stream().forEach(d->d.setDriverAgencyName(null));
//                    nameList.stream().map((s)->s.toUpperCase()).forEach((s)->System.out.println(s));
            this.cabAgencyRepository.deleteById(cabAgencyId);
            return findCabAgency.get();
        }
        else throw new CabAgencyNotFoundException("No such cab agency found");

    }





    public String getCookie(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("jwt")) {
                   return cookie.getValue();
                }
            }
        }
        return "null";
    }
}

