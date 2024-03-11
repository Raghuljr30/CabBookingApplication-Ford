package com.cabBooker.cabBookingApplication.driver.padma;

import com.cabBooker.cabBookingApplication.driver.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class DriverServiceTests {

    @Autowired
     public DriverService driverService;

    @Test
    public void nullDriverIdTestExceptionMessageInGetDriverById()
    {
        try {
            driverService.getDriverById(null);
        } catch (nullFieldException | noChangeException e) {
            Assertions.assertEquals("DriverID can't be null",e.getMessage());
        }
    }

    @Test
    public void nullDriverIdTestExceptionMessageInDeleteDriverById()
    {
        try {
            driverService.deleteDriverById(null);
        } catch (nullFieldException e) {
            Assertions.assertEquals("DriverID can't be null",e.getMessage());
        }
    }

    @Test
    public void nullAgencyNameExceptionMessageInGetDriversByAgencyName()
    {
        try {
            driverService.getDriversByAgencyName(null);
        } catch (emptyListException | nullFieldException e) {
            Assertions.assertEquals("AgencyName can't be null",e.getMessage());
        }
    }

    @Test
    public void nullDriverIdTestExceptionMessageInRegisterNewDriver()
    {
        try {
            driverService.registerNewDriver(new Driver(1,null,"padma@gmail.com","padma@12",9876543211L,890,78,"Ola",1,null));
        } catch (nullFieldException e) {
            Assertions.assertEquals("Driver Details can't be null",e.getMessage());
        }
    }



//    @Test
//    void noDriversPresentTestInGetDriversByAgencyName() throws nullFieldException, emptyListException {
////        try {
////            this.driverService.getDriversByAgencyName("Rapido");
////        } catch (emptyListException | nullFieldException e) {
////            Assertions.assertEquals("No drivers are present in this agency",e.getMessage());
////        }
//        Driver d1= new Driver(1,"Padma","padma@gmail.com","padma@12",9876543211L,890,78,"Ola",1,null);
//        Driver d2= new Driver(2,"Jayashree","jaya@gmail.com","jaya@12",9876543210L,899,98,"Uber",2,null);
//        List<Driver> Agency = new ArrayList<>();
//        Agency.add(d1);
//        Agency.add(d2);
//        Assertions.assertNotNull(d1);
//        Assertions.assertNotNull(d2);
//        Assertions.assertNotNull(Agency);
//        try{
//        if((d1.getDriverAgencyName().equals(driverService.getDriversByAgencyName("Rapido"))||(d2.getDriverAgencyName().equals(driverService.getDriversByAgencyName("Rapido")))))
//        {
//
//        }
//        }
//            Assertions.assertEquals("No drivers are present in this agency",e.getMessage());
//
//    }

    @Test
    public void nullDriverTestExceptionMessageInUpdateDriver()
    {
        try {
            driverService.updateDriver(null,null);
        } catch (nullFieldException e) {
            Assertions.assertEquals("DriverId can't be null",e.getMessage());
        }
    }



    @Test
    public void noSuchDriverExistsExceptionMessageInUpdateDriver()
    {
        try {
            driverService.updateDriver(2,new Driver(2,
                    "Padma",
                    "padma@gmail.com",
                    "padma@12",
                    9876543210L,
                    7890,
                    112,
                    "Ola",
                    01,
                    null));

        } catch (nullFieldException e) {
            Assertions.assertEquals("No such Driver Exists: 2",e.getMessage());
        }
    }

    @Test
    public void noSuchDriverExistsInGetDriverById()
    {
        try {
            driverService.getDriverById(5);
        }catch (noChangeException | nullFieldException e)
        {
            Assertions.assertEquals("No such Id Exists: 5",e.getMessage());

        }
    }

    @Test
    public void noSuchDriverExistsInUpdateDriver()
    {
        try {
            driverService.updateDriver(1,new Driver( 1,
                    "Padma",
                    "padma@gmail.com",
                    "padma@12",
                    9876543210L,
                    7890,
                    112,
                    "Ola",
                    01,
                    null));
        }catch (nullFieldException e)
        {
            Assertions.assertEquals("No such Driver Exists: 1",e.getMessage());
        }
    }

    @Test
    void ListAllDriversTest() {
        try {
            this.driverService.listAllDrivers();
        } catch (emptyListException e) {
            e.printStackTrace();
        }

    }

    @Test
    void getDriversByAgencyNameTest(){
        List<Driver> drivers = null;
        try{
            drivers = this.driverService.getDriversByAgencyName("Ola");
            Assertions.assertNotNull(drivers);
        }catch (emptyListException | nullFieldException e)
        {
            Assertions.fail(e.getMessage());
        }
    }


    @Test
    public void nullIdTestExceptionMessageInChangeMobileNumber()
    {
        try {
            driverService.changeMobileNumber(null,9876543267L);
        } catch (nullFieldException | noChangeException e) {
            Assertions.assertEquals("DriverId can't be empty",e.getMessage());
        }
    }

    @Test
    public void nullPhNumberTestExceptionMessageInChangeMobileNumber()
    {
        try {
            driverService.changeMobileNumber(1,null);
        } catch (nullFieldException | noChangeException e) {
            Assertions.assertEquals("Driver's Mobile Number can't be empty",e.getMessage());
        }
    }

    @Test
    public void checkPhNumberTestExceptionMessageInChangeMobileNumber() throws nullFieldException, noChangeException {

       try {
           Driver driver = new Driver(1,
                   "Padma",
                   "padma@gmail.com",
                   "padma@12",
                   9876543210L,
                   7890,
                   112,
                   "Ola",
                   01,
                   null);
           driverService.registerNewDriver(driver);

           // Update mobile number
           Long newMobileNumber = 9876543210L;
           driverService.changeMobileNumber(driver.getDriverId(), newMobileNumber);

           Optional<Driver> updatedDriver = driverService.getDriverById((driver.getDriverId()));
           Assertions.assertEquals(newMobileNumber, updatedDriver.get().getDriverMobileNumber());
       }catch(noChangeException e) {
           Assertions.assertEquals("No change detected", e.getMessage());
       }
    }

    @Test
    public void checkPasswordTestExceptionMessageInChangeMobileNumber() throws nullFieldException, noChangeException {

        try {
            Driver driver = new Driver(1,
                    "JJ",
                    "jj@gmail.com",
                    "jj@12",
                    9876543210L,
                    7890,
                    112,
                    "Ola",
                    01,
                    null);
            driverService.registerNewDriver(driver);

            String newPassword = "jj@gmail.com";
            driverService.changePassword(driver.getDriverId(), newPassword);

            Optional<Driver> updatedDriver = driverService.getDriverById((driver.getDriverId()));
            Assertions.assertEquals(newPassword, updatedDriver.get().getDriverPassword());
        }catch(noChangeException e) {
            Assertions.assertEquals("No change detected", e.getMessage());
        }


    }
    @Test
    public void testChangeMobileNumber() throws nullFieldException, noChangeException {

        Driver driver = new Driver(1,
                "JJ",
                "jj@gmail.com",
                "jj@12",
                9876543210L,
                7890,
                112,
                "Ola",
                01,
                null);
        driverService.registerNewDriver(driver);

        Long newMobileNumber = 9876543210L;

        Optional<Driver> updatedDriver = driverService.getDriverById(driver.getDriverId());

        // Verify if the mobile number has been updated
        Assertions.assertNotNull(updatedDriver);
        Assertions.assertEquals(newMobileNumber, updatedDriver.get().getDriverMobileNumber());
    }
}






