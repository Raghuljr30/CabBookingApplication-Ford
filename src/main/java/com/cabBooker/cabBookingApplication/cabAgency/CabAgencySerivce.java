package com.cabBooker.cabBookingApplication.cabAgency;

import java.util.List;

public interface CabAgencySerivce {

    public CabAgency registerNewCabAgency(CabAgency newCabAgency);
    public List<CabAgency> displayCabAgency();

    public  CabAgency mapCabAgencyAndCab(String agencyname);

}