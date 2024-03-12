package com.cabBooker.cabBookingApplication.CabAgencyTest;

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
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
                        .cabAgencyEmail("raghul@gmail.com").cabAgencyPassword("raghulpasswprd")
                        .cabAgencyMobileNumber(1234455L).cabs(Arrays.asList(cab)).drivers(Arrays.asList(driver)).build();
        cabAgencyRepository.save(cabAgency);


    }



    @Test
    public void cabRegistrationTest()
    {
        Driver driver= Driver.builder().driverId(1).build();
        Cab cab=Cab.builder().cabId(1).build();
        List<Cab>cabList=new ArrayList<>();
        cabList.add(cab);
        List<Driver>driverList=new ArrayList<>();
        driverList.add(driver);

//        Booking booking=Booking.builder().bookingId(1).build();
//        List<Booking>bookingList=new ArrayList<>();
//        bookingList.add(booking);

            try {
                Assertions.assertNotNull(cabAgencySerivce.registerNewCabAgency(CabAgency.builder().cabAgencyName("RAGHUL")
                        .cabAgencyEmail("raghul@gmail.com").cabAgencyPassword("raghul").cabAgencyMobileNumber(1234L)
                        .bookings(null).cabs(cabList).drivers(driverList).build()
                ));
            } catch (CabAgencyMissingInputFieldException e) {
                Assertions.fail(e.getMessage());
            } catch (CabAgencyCreationException e) {
                throw new RuntimeException(e);
            }
    }

    @Test
    public void testRegistNewCabAgencyThrowsException()
    {

        Assertions.assertThrows(NullPointerException.class,()-> this.cabAgencySerivce.registerNewCabAgency(null));
    }




//    public void CabRegistrationThrowException()
//    {
//        Assertions.assertThrows(CabAgencyCreationException.class,
//                ()->cabAgencySerivce.registerNewCabAgency(CabAgency.builder().cabAgencyId(null).build()));
//    }


    @Test
    public void cabAgencyById()
    {
        Assertions.assertNotNull(cabAgencySerivce.displayCabAgency());
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

    @Test
    public void mapCabAgencyAndCabUsingIdTestThrowsException()
    {
        Assertions.assertThrows(CabAgencyNotFoundException.class,
                ()->cabAgencySerivce.mapCabAgencyAndCabUsingId(66));


    }

    @Test
    public void mapCabAgencyAndDriversUsingId()
    {
        try {
            Assertions.assertNotNull(cabAgencySerivce.mapCabAgencyAndCabUsingId(1));
            Assertions.assertNotEquals(0,cabRepository.findAllById(Collections.singleton(1)).size());
        } catch (CabAgencyNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

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
            Assertions.assertNotNull(cabAgencySerivce.updateCabPickUpLocationAndDropLocationInCabAgency(1,1,"kerala","bangalore"));

        } catch (CabAgencyNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CabAgencyMissingInputFieldException e) {
            throw new RuntimeException(e);
        } catch (CabNotFoundInCabAgencyException e) {
            throw new RuntimeException(e);
        }


        Assertions.assertNotNull(cabRepository.findAllById(Collections.singleton(1)));

    }

    @Test
    public void test_toCheck_ifLocations_gotUpdated()
    {
        try {
            cabAgencySerivce.updateCabPickUpLocationAndDropLocationInCabAgency(1,1,"kerala","bangalore");
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
    public void test_toCheck_ifLocation_NotGotUpdated_ThrowsEception()
    {
        try {
            cabAgencySerivce.updateCabPickUpLocationAndDropLocationInCabAgency(1,1,"kerala","bangalore");
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

            try {
                cabAgencySerivce.updateCabAgencyMobileNumberById(1,1234L);
                CabAgency cabAgencyUpdatedMobileNumber=cabAgencyRepository.findById(2).get();
                Assertions.assertEquals(1234L,cabAgencyUpdatedMobileNumber.getCabAgencyMobileNumber());
            } catch (CabAgencyNotFoundException e) {
                throw new RuntimeException(e);
            } catch (CabAgencyMissingInputFieldException e) {
                throw new RuntimeException(e);
            }


    }

    @Test
    public void test_checkIf_cabAgency_password_gotUpdated()
    {
        try {
            cabAgencySerivce.updateCabAgencyPasswordById(1,"newPassword");
        } catch (CabAgencyNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CabAgencyMissingInputFieldException e) {
            throw new RuntimeException(e);
        }
        CabAgency cabAgencyUpdatedPassword=cabAgencyRepository.findById(1).get();
        Assertions.assertEquals("newPassword",cabAgencyUpdatedPassword.getCabAgencyPassword());
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




