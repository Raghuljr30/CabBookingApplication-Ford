package com.cabBooker.cabBookingApplication.Authentication;

import com.cabBooker.cabBookingApplication.cabAgency.CabAgency;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgencyRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;

@Service
public class CabAgencyAuthentication {

    @Autowired
    private CabAgencyRepository cabAgencyRepository;
    public CabAgency authenticate(@CookieValue("jwt") String jwt) throws  CabAgencyNotAuthenticatedException
    {
        if(jwt == null)
            throw new CabAgencyNotAuthenticatedException("Unauthenticated !");

        Claims claim=null;
        String email=null;

        try{
            claim = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(jwt).getBody();
            email = claim.getIssuer();
        }
        catch (ExpiredJwtException e){
            throw new CabAgencyNotAuthenticatedException("JWT got Expired please log in again.");

        }
        catch (SignatureException e){
            throw new CabAgencyNotAuthenticatedException("JWT Signature Exception.");
        }

        catch (Exception e){
            throw  new CabAgencyNotAuthenticatedException("Unauthenticated !");
        }

        return this.cabAgencyRepository.findByCabAgencyEmail(email);

    }
}
