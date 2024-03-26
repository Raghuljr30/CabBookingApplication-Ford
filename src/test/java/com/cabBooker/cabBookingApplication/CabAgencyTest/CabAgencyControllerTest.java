package com.cabBooker.cabBookingApplication.CabAgencyTest;

import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabRepository;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgency;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgencyController;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgencyDto;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgencyRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CabAgencyControllerTest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTestTemplate;

    @Autowired
    private CabAgencyRepository cabAgencyRepository;

    @Autowired
    private CabRepository cabRepository;

   @BeforeEach
    public void registTest(){

       cabRepository.save(Cab.builder().cabId(1).cabAgencyId(1).pickUpPoint("Chennai").dropPoint("Salem").build());
       CabAgency cabAgency= this.restTestTemplate.postForObject("http://localhost:" + port + "/cabagency",CabAgency.builder().cabAgencyId(1).cabAgencyName("RAGHUL")
                .cabAgencyEmail("raghul@gmail.com").cabAgencyPassword("raghul")
                .cabAgencyMobileNumber(1234455L). build(), CabAgency.class);
    }

    @AfterEach
    public void delete()
    {
        cabAgencyRepository.deleteAll();
    }

    @Test
    public void loginTest()
    {
        CabAgency cabAgency= this.restTestTemplate.getForObject("http://localhost:" + port + "/cabagency/login", CabAgency.class);
        Assertions.assertNotNull(cabAgency);

    }

    @Test
    public void getCabAgencyByIdTest()
    {
        System.out.println(cabAgencyRepository.findAll()+"_-------------");
       CabAgency cabAgency= this.restTestTemplate.getForObject("http://localhost:" + port + "/cabagency/1", CabAgency.class);
       Assertions.assertEquals("RAGHUL",cabAgency.getCabAgencyName());
    }

    @Test
    public void getCabAgencyByIdTestThrowsException()
    {
        String cabAgencyErrorMessage= this.restTestTemplate.getForObject("http://localhost:" + port + "/cabagency/2", String.class);
        Assertions.assertEquals("No such Cab Agency found",cabAgencyErrorMessage);
    }









}
