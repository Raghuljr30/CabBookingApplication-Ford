package com.cabBooker.cabBookingApplication.CabAgencyTest;

import com.cabBooker.cabBookingApplication.Authentication.CabAgencyAuthentication;
import com.cabBooker.cabBookingApplication.Authentication.CabAgencyNotAuthenticatedException;
import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabRepository;
import com.cabBooker.cabBookingApplication.cab.CabService;
import com.cabBooker.cabBookingApplication.cabAgency.*;

import com.cabBooker.cabBookingApplication.customer.Customer;
import com.cabBooker.cabBookingApplication.customer.CustomerService;
import com.cabBooker.cabBookingApplication.driver.Driver;
import com.cabBooker.cabBookingApplication.driver.DriverRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CabAgencyServiceTest {

    @Autowired
    private  CabAgencyRepository cabAgencyRepository;

    @Autowired
    private CabAgencySerivce cabAgencySerivce;

    @Autowired
    private CabRepository cabRepository;

    @Autowired
    private CabService cabService;

    @Autowired
    private DriverRepository driverRepository;

    @BeforeEach
    public void createCabAgency()
    {
        Driver driver=Driver.builder().driverAgencyId(1).driverId(10).driverName("Dinesh"
                ).driverAgencyName("RAGHUL").driverEmail("dinesh@gmail.com")
                .driverPassword("dinesh").driverMobileNumber(12234L)
                .driverVehicleNumber(1234).licenseNumber(3455)
                .reviews(null).build();
        driverRepository.save(driver);

        Cab cab=Cab.builder().cabId(20).cabModel("xuv").numberOfSeats(6).vehicleNumber(1234)
                        .availability(true).agencyName("RAGHUL").cabAgencyId(1)
                        .pickUpPoint("Chennai").dropPoint("Trichy").fair(500.0).build();
        cabRepository.save(cab);

        CabAgency cabAgency=CabAgency.builder().cabAgencyId(1).cabAgencyName("RAGHUL")
                        .cabAgencyEmail("raghul@gmail.com").cabAgencyPassword("raghul")
                        .cabAgencyMobileNumber(1234455L).cabs(Arrays.asList(cab)).drivers(Arrays.asList(driver)).build();
        cabAgencyRepository.save(cabAgency);


    }

    @Test
    public void test_toCheck_registerNewCabAgency()
    {
        CabAgencyDto newCabAgency= new CabAgencyDto("Conor","conor@gmail.com","conor",123456L);
        try {
           Assertions.assertNotNull(cabAgencySerivce.registerNewCabAgency(newCabAgency));
        } catch (CabAgencyMissingInputFieldException e) {
            throw new RuntimeException(e);
        } catch (CabAgencyCreationException e) {
            throw new RuntimeException(e);
        } catch (AccountAlreadyExistException e) {
            throw new RuntimeException(e);
        } catch (CabAgencyNotAuthenticatedException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testRegistNewCabAgencyThrowsException()
    {

        Assertions.assertThrows(NullPointerException.class,()-> this.cabAgencySerivce.registerNewCabAgency(null));
    }






    @Test
    public void loginCabAgencyTest()
    {
        HttpServletResponse response=mock(HttpServletResponse.class);
        CabAgencyLoginDto loginRequest=new CabAgencyLoginDto(1,"raghul@gmail.com","raghul");
        try {
            Assertions.assertEquals(true,cabAgencySerivce.loginCabAgency(loginRequest,response));
        } catch (CabAgencyNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void loginCabAgencyThrowExceptionTest()
    {
        HttpServletResponse response=mock(HttpServletResponse.class);
        CabAgencyLoginDto loginRequest=new CabAgencyLoginDto(100,"raghul@gmail.com","raghulkfms");

        Assertions.assertThrows(CabAgencyNotAuthenticatedException.class,
                ()->cabAgencySerivce.loginCabAgency(loginRequest,response));


    }


    @Test
    public void displayCabAgencyTest()
    {
        HttpServletRequest request=mock(HttpServletRequest.class);
       Assertions.assertThrows(CabAgencyNotAuthenticatedException.class,()->cabAgencySerivce.displayCabAgency(request));
    }

    @Test
    public void displayCabAgency_NotAuthenticatedTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        Cookie cookie = mock(Cookie.class);
        when(cookie.getName()).thenReturn("someOtherCookieName");
        when(cookie.getValue()).thenReturn("invalidToken");
        when(request.getCookies()).thenReturn(new Cookie[]{cookie});

        CabAgencyAuthentication cabAgencyAuthentication = mock(CabAgencyAuthentication.class);
        try {
            when(cabAgencyAuthentication.authenticate(anyString())).thenThrow(new CabAgencyNotAuthenticatedException("Authentication failed"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertThrows(CabAgencyNotAuthenticatedException.class, () -> {
            cabAgencySerivce.displayCabAgency( request);
        });
    }

    @Test
    public void displayCabAgencyByIdTest()
    {
        HttpServletRequest request=mock(HttpServletRequest.class);
        CabAgency cabAgency= null;

        try {
            cabAgency = cabAgencySerivce.displayCabAgencyById(1,request);
            Assertions.assertNotNull(cabAgency);
        } catch (CabAgencyNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CabAgencyNotAuthenticatedException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void displayCabAgencyById_NotAuthenticatedTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        Cookie cookie = mock(Cookie.class);
        when(cookie.getName()).thenReturn("someOtherCookieName");
        when(cookie.getValue()).thenReturn("invalidToken");
        when(request.getCookies()).thenReturn(new Cookie[]{cookie});

        CabAgencyAuthentication cabAgencyAuthentication = mock(CabAgencyAuthentication.class);
        try {
            when(cabAgencyAuthentication.authenticate(anyString())).thenThrow(new CabAgencyNotAuthenticatedException("Authentication failed"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertThrows(CabAgencyNotAuthenticatedException.class, () -> {
            cabAgencySerivce.displayCabAgencyById(2, request);
        });
    }
    @Test
    public void displayCabAgencyByIdThrowsException()
    {
        HttpServletRequest request=mock(HttpServletRequest.class);
        Assertions.assertThrows(CabAgencyNotFoundException.class,()->cabAgencySerivce.displayCabAgencyById(222,request));

    }




    @Test
    public void mapCabAgencyAndCabUsingIdTest()
    {
        try {
            Assertions.assertNotNull(cabAgencySerivce.mapCabAgencyAndCabUsingId(1));
            Assertions.assertNotNull(cabRepository.findAllById(Collections.singleton(1)));
        } catch (CabAgencyNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
//
    @Test
    public void mapCabAgencyAndCabUsingIdTestThrowsException()
    {
        Assertions.assertThrows(CabAgencyNotFoundException.class,
                ()->cabAgencySerivce.mapCabAgencyAndCabUsingId(66));


    }
//
    @Test
    public void mapCabAgencyAndDriversUsingId()
    {
        try {
            Assertions.assertNotNull(cabAgencySerivce.mapCabAgencyAndCabUsingId(1));
            Assertions.assertNotNull(driverRepository.findAllById(Collections.singleton(1)).size());
        } catch (CabAgencyNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
//
    @Test
    public void mapCabAgencyAndDriversUsingIdThrowsException()
    {
       Assertions.assertThrows(
               CabAgencyNotFoundException.class,
               ()->cabAgencySerivce.mapCabAgencyAndDriversUsingId(9));

    }

    @Test
    public void testUpdateCabPickUpLocationAndDropLocationInCabAgency()
    {
        try {
            Assertions.assertNotNull(cabAgencySerivce.updateCabPickUpLocationAndDropLocationInCabAgency(1,1,"kerala","bangalore",500));

        } catch (CabAgencyNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CabAgencyMissingInputFieldException e) {
            throw new RuntimeException(e);
        } catch (CabNotFoundInCabAgencyException e) {
            throw new RuntimeException(e);
        }


        Assertions.assertNotNull(cabRepository.findAllById(Collections.singleton(1)));

    }
//
    @Test
    public void test_toCheck_ifLocations_gotUpdated()
    {
        try {
            cabAgencySerivce.updateCabPickUpLocationAndDropLocationInCabAgency(1,1,"kerala","bangalore",500);
            Cab cabLocationUpdated=cabRepository.findById(1).get();
            Assertions.assertEquals("kerala",cabLocationUpdated.getPickUpPoint());
            Assertions.assertEquals("bangalore",cabLocationUpdated.getDropPoint());

        } catch (CabAgencyNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CabNotFoundInCabAgencyException e) {
            throw new RuntimeException(e);
        } catch (CabAgencyMissingInputFieldException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void test_toCheck_ifLocations_gotUpdated_throwsException()
    {
        Assertions.assertThrows(CabNotFoundInCabAgencyException.class,()->
                cabAgencySerivce.updateCabPickUpLocationAndDropLocationInCabAgency(1,55,"kerala","bangalore",500));

        Assertions.assertThrows(CabAgencyMissingInputFieldException.class,()->cabAgencySerivce.updateCabPickUpLocationAndDropLocationInCabAgency(1,1,null,null,500));

        Assertions.assertThrows(NoSuchElementException.class,()->cabAgencySerivce.updateCabPickUpLocationAndDropLocationInCabAgency(123,1,"kerala","bangalore",500));

    }

    @Test
    public void test_toCheck_ifLocation_NotGotUpdated_ThrowsEception()
    {
        try {
            cabAgencySerivce.updateCabPickUpLocationAndDropLocationInCabAgency(1,1,"kerala","bangalore",500);
            Cab cabLocationUpdated=cabRepository.findById(1).get();
            Assertions.assertNotEquals("pune",cabLocationUpdated.getPickUpPoint());
        } catch (CabAgencyNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CabNotFoundInCabAgencyException e) {
            throw new RuntimeException(e);
        } catch (CabAgencyMissingInputFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test_checkIf_CabAgency_MobileNumber_gotUpdated()
    {
        CabAgencyMobileNumberDto cabAgencyMobileNumberDto=new CabAgencyMobileNumberDto(1234L);
            try {
              CabAgency cabAgency=  cabAgencySerivce.updateCabAgencyMobileNumberById(1,cabAgencyMobileNumberDto);
                CabAgency cabAgencyUpdatedMobileNumber=cabAgencyRepository.findById(1).get();
                Assertions.assertEquals(cabAgency.getCabAgencyMobileNumber(),cabAgencyUpdatedMobileNumber.getCabAgencyMobileNumber());
            } catch (CabAgencyNotFoundException e) {
                throw new RuntimeException(e);
            } catch (CabAgencyMissingInputFieldException e) {
                throw new RuntimeException(e);
            }


    }

    @Test
    public void test_checkIf_CabAgency_MobileNumber_gotUpdated_throwsException()
    {
        CabAgencyMobileNumberDto cabAgencyMobileNumberDto=new CabAgencyMobileNumberDto(1234L);

        Assertions.assertThrows(CabAgencyNotFoundException.class,()->cabAgencySerivce.updateCabAgencyMobileNumberById(111,cabAgencyMobileNumberDto));
    }

    @Test
    public void test_checkIf_cabAgency_password_gotUpdated()
    {
        CabAgencyPasswordDto cabAgencyPasswordDto=new CabAgencyPasswordDto("raghulnew","raghulnew");
        List<CabAgency> cabAgencies=cabAgencyRepository.findAll();
        System.out.println("----------_______________-------------------____");
                for(int i=0;i<cabAgencies.size();i++)
                {
                    System.out.println(cabAgencies.get(i).getCabAgencyId());
                }
//        try {
//
//           CabAgency cabAgency= cabAgencySerivce.updateCabAgencyPasswordById(1,cabAgencyPasswordDto);
//            CabAgency cabAgencyUpdatedPassword=cabAgencyRepository.findById(1).get();
//            Assertions.assertEquals(cabAgency.getCabAgencyPassword(),cabAgencyUpdatedPassword.getCabAgencyPassword());
//        } catch (CabAgencyNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (CabAgencyMissingInputFieldException e) {
//            throw new RuntimeException(e);
//        }

    }

    @Test
    public void test_checkIf_cabAgency_password_gotUpdated_throwsException()
    {
        CabAgencyPasswordDto cabAgencyPasswordDto=new CabAgencyPasswordDto(null,"raghulnew");

        Assertions.assertThrows(CabAgencyMissingInputFieldException.class,()->cabAgencySerivce.updateCabAgencyPasswordById(1,cabAgencyPasswordDto));
        Assertions.assertThrows(CabAgencyNotFoundException.class,()->cabAgencySerivce.updateCabAgencyPasswordById(12121,cabAgencyPasswordDto));
    }




    @Test
    public  void test_if_cabAgency_got_deleted() throws CabAgencyNotFoundException {
       Assertions.assertNotNull(cabAgencySerivce.deleteCabAgencyById(1));
    }










//    @Test
//    public void createCabAgencyTest()
//    {
//        CabAgency cabAgency=new CabAgency();
//        cabAgency.setCabAgencyMobileNumber(1234L);
//        cabAgency.setCabAgencyId(1);
//        cabAgency.setCabAgencyName("raina");
//        cabAgency.setCabAgencyPassword("raina");
//        cabAgency.setCabAgencyEmail("raina@gmail.com");
//        cabAgency.setCabs(null);
//        cabAgency.setDrivers(null);
//        cabAgency.setBookings(null);
//        CabAgencySerivce cabAgencySerivce=new CabAgencyServiceImplementation();
//        try {
//            Assertions.assertNotNull(cabAgencySerivce.registerNewCabAgency(cabAgency));
//        } catch (CabAgencyCreationException e) {
//            throw new RuntimeException(e);
//        } catch (CabAgencyMissingInputFieldException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    public void createCabAgencyThrowNullException()
//    {
//        CabAgency cabAgency=new CabAgency();
//        cabAgency.setCabAgencyId(null);
//        CabAgencySerivce cabAgencySerivce=new CabAgencyServiceImplementation();
//
//         Assertions.assertThrows(CabAgencyCreationException.class,
//                 ()->cabAgencySerivce.registerNewCabAgency(cabAgency));
//    }
//
//    @Test
//    public void mapCabAgencyAndCabByIdTest()
//    {
//        CabAgency cabAgency=new CabAgency();
//        cabAgency.setCabAgencyId(1);
//        cabAgencyRepository.save(cabAgency);
//
//        CabAgency cabAgency1=cabAgencyRepository.findById(1).get();
//        Assertions.assertNotNull(cabAgency1);
//
//    }
//
//    @Test
//    public void CabAgencyNotFoundThrowException()
//    {
//        CabAgency cabAgency=new CabAgency();
//        cabAgency.setCabAgencyId(1);
//        cabAgencyRepository.save(cabAgency);
//
//        Assertions.assertThrows(NoSuchElementException.class,
//                ()->cabAgencyRepository.findById(2).get());
//    }
//
//    @Test
//    public void CabNotFoundThrowException()
//    {
//        Cab cab=new Cab();
//
//        cab.setCabId(1);
//        cabRepository.save(cab);
//
//        Assertions.assertThrows(NoSuchElementException.class,
//                ()->cabRepository.findById(2).get());
//    }
//
//    @Test
//    public void updateLocation()
//    {
//        CabAgency cabAgency=new CabAgency();
//        cabAgency.setCabAgencyId(1);
//        cabAgencyRepository.save(cabAgency);
//
//        Assertions.assertNotNull(cabAgencyRepository.findById(1).get());
//
//        Cab cab=new Cab();
//        cab.setCabId(1);
//        cab.setPickUpPoint("chennai");
//        cab.setDropPoint("madurai");
//        cabRepository.save(cab);
//
//        Assertions.assertNotNull(cabRepository.findById(1).get());
//
//        Assertions.assertEquals("chennai",cabRepository.findById(1).get().getPickUpPoint());
//
//        Assertions.assertEquals("madurai",cabRepository.findById(1).get().getDropPoint(),"madurai");
//    }
//
//
//    @Test
//    public void updateLocationthrowsException()
//    {
//        CabAgency cabAgency=new CabAgency();
//        cabAgency.setCabAgencyId(1);
//        cabAgencyRepository.save(cabAgency);
//
////        Assertions.assertNotNull(cabAgencyRepository.findById(1).get());
//
//        Cab cab=new Cab();
//        cab.setCabId(1);
//        cab.setPickUpPoint("chennai");
//        cab.setDropPoint("madurai");
//        cabRepository.save(cab);
//
////        Assertions.assertNotNull(cabRepository.findById(1).get());
//
//        Assertions.assertNotEquals("trichy",cabRepository.findById(1).get().getPickUpPoint());
//
//        Assertions.assertNotEquals("coimbatore",cabRepository.findById(1).get().getDropPoint(),"madurai");
//    }
//
//    @Test
//    public void updateCabAgencyMobileNumberTest()
//    {
//        CabAgency cabAgency=new CabAgency();
//        cabAgency.setCabAgencyId(1);
//        cabAgency.setCabAgencyMobileNumber(1234L);
//        cabAgencyRepository.save(cabAgency);
//        Assertions.assertNotNull(cabAgencyRepository.findById(1).get());
//
//        cabAgencyRepository.findById(1).get().setCabAgencyMobileNumber(2345L);
//
//        Assertions.assertEquals(2345,cabAgencyRepository.findById(1).get().getCabAgencyMobileNumber());
//    }
//
//    @Test
//    public void updateCabAgencyMobileNumberNotUpdatedTest()
//    {
//        CabAgency cabAgency=new CabAgency();
//        cabAgency.setCabAgencyId(1);
//        cabAgency.setCabAgencyMobileNumber(1234L);
//        cabAgencyRepository.save(cabAgency);
//        Assertions.assertNotNull(cabAgencyRepository.findById(1).get());
//
//        cabAgencyRepository.findById(1).get().setCabAgencyMobileNumber(2345L);
//
//        Assertions.assertNotEquals(234,cabAgencyRepository.findById(1).get().getCabAgencyMobileNumber());
//    }
//
//
//    @Test
//    public void updateCabAgencyPasswordTest()
//    {
//        CabAgency cabAgency=new CabAgency();
//        cabAgency.setCabAgencyId(1);
//        cabAgency.setCabAgencyPassword("rag");
//        cabAgencyRepository.save(cabAgency);
//        Assertions.assertNotNull(cabAgencyRepository.findById(1).get());
//
//        cabAgencyRepository.findById(1).get().setCabAgencyPassword("den");
//
//        Assertions.assertEquals("den",cabAgencyRepository.findById(1).get().getCabAgencyPassword());
//    }
//
//    @Test
//    public void updateCabAgencyPasswordNotUpdatedTest()
//    {
//        CabAgency cabAgency=new CabAgency();
//        cabAgency.setCabAgencyId(1);
//        cabAgency.setCabAgencyPassword("rag");
//        cabAgencyRepository.save(cabAgency);
//        Assertions.assertNotNull(cabAgencyRepository.findById(1).get());
//
//        cabAgencyRepository.findById(1).get().setCabAgencyPassword("den");
//
//        Assertions.assertNotEquals("abc",cabAgencyRepository.findById(1).get().getCabAgencyPassword());
//    }
//
//    @Test
//    public void deleteCabAgencyTest()
//    {
//        CabAgency cabAgency=new CabAgency();
//        cabAgency.setCabAgencyId(1);
//        cabAgencyRepository.save(cabAgency);
//        cabAgencyRepository.deleteById(1);;
//
//        Assertions.assertThrows(NoSuchElementException.class,
//                ()->cabAgencyRepository.findById(1).get());
//    }



//    @Test
//    public void registerCabAgencyTest() {
//
//        Driver driver= Driver.builder().driverId(1).build();
//        Cab cab=Cab.builder().cabId(1).build();
//        List<Cab>cabList=new ArrayList<>();
//        cabList.add(cab);
//        List<Driver>driverList=new ArrayList<>();
//        driverList.add(driver);
//
//        Booking booking=Booking.builder().bookingId(1).build();
//        List<Booking>bookingList=new ArrayList<>();
//        bookingList.add(booking);
//
//            try {
//                Assertions.assertNotNull(cabAgencySerivce.registerNewCabAgency(CabAgency.builder().cabAgencyId(1).cabAgencyName("RAGHUL")
//                        .cabAgencyEmail("raghul@gmail.com").cabAgencyPassword("raghul")
//                        .bookings(bookingList).cabs(cabList).drivers(driverList).build()
//                ));
//            } catch (CabAgencyMissingInputFieldException e) {
//                throw new RuntimeException(e);
//            } catch (CabAgencyCreationException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//


    }




