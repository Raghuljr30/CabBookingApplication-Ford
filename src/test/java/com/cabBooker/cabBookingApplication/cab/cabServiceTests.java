package com.cabBooker.cabBookingApplication.cab;

import com.cabBooker.cabBookingApplication.driver.Driver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest

public class cabServiceTests {
    @Autowired
    private CabService cabService;


    @Test
    void noCabsExistTestInGetAllCabs() {
        try {
            this.cabService.displayAllCabs();
        } catch (displayAllCabException e) {
            Assertions.assertEquals("No cab Exists", e.getMessage());
        }
    }
    @Test
    void nullCabTestExceptionMessageIndeleteCabByID()
    {
        try {
            this.cabService.deleteCabById(null);
        } catch (deleteCabByIdException e) {
            Assertions.assertEquals("cabId can't be null",e.getMessage());
        }
    }
    @Test

    void registerCabsTest() {
        try {
            Cab cab= null;
            cab = this.cabService.registerNewCab(new Cabdto(1,"swift",true,4,567,true,"ola","cmbt","kilambakkam",78.90,900));
            cab.setDriver(new Driver(1,"kumar","kumar@gmail.com","123456",9876543210L,456,2398,"ola",null));
            Assertions.assertNotNull(cab);
        } catch (CabException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    public void noSuchCabExistsExceptionMessageInUpdateCab()
    {
        try {
            cabService.updateCab(2, new Cab(2,"toyota",true,4,654,true,555,"ola","cmbt","padi",90.0,null));


        } catch (CabException e) {
            Assertions.assertEquals("No such cab Exists",e.getMessage());
        }
    }
   @Test
   void noSuchAgencynameExistsIngetCabByAgencyNameTest(){

       try{
           cabService.getCabByAgencyName("ola");

       }catch (CabException e)
       {
           Assertions.assertEquals("No such Agency name Exists",e.getMessage());
       }
   }
   @Test
   void nullCabAgencyNameIngetCabByagencyNametest(){
        try{
            cabService.getCabByAgencyName(null);
        }
        catch(CabException e){
            Assertions.assertEquals("Agency name can't be null",e.getMessage());

       }
   }
  @Test

  public void nullCabIdTestExceptionMessageInGetCabById()
  {
      try {
          cabService.getCabById(null);
      } catch (getCabByIdException e) {
          Assertions.assertEquals("cabId can't be null",e.getMessage());
      }
  }



}

