package com.cabBooker.cabBookingApplication.Authentication;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {


    private String jwtToken;

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }


}