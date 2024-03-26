package com.cabBooker.cabBookingApplication.CabAgencyTest;

import com.cabBooker.cabBookingApplication.Authentication.CabAgencyNotAuthenticatedException;
import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabRepository;
import com.cabBooker.cabBookingApplication.cabAgency.*;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CabAgencyServiceTestByMockRepository {

    @MockBean
    private CabAgencyRepository cabAgencyMockRepository ;

    @MockBean
    private CabRepository cabMockRepository ;

    @Autowired
    private CabAgencySerivce cabAgencySerivce;



    @Test
    public void getCabAgencyByIdTest()
    {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(this.cabAgencyMockRepository.findById(1)).thenReturn(Optional.of(CabAgency.builder().cabAgencyId(1).build()));

        try {
            Assertions.assertEquals(1,cabAgencySerivce.displayCabAgencyById(1,request).getCabAgencyId());
        } catch (CabAgencyNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CabAgencyNotAuthenticatedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getCabAgencyByIdThrowsExceptionTest()
    {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(this.cabAgencyMockRepository.findById(100)).thenReturn(Optional.empty());

        Assertions.assertThrows(CabAgencyNotFoundException.class,()->cabAgencySerivce.displayCabAgencyById(100,request));

    }

    @Test
    public void updateCabLocationTest()
    {
        when(cabAgencyMockRepository.findById(any())).thenReturn(Optional.ofNullable(CabAgency.builder().cabAgencyId(1).build()));
        when(cabMockRepository.findById(any())).
                thenReturn(Optional.of(Cab.builder().
                        cabAgencyId(1).
                        cabId(1).
                        pickUpPoint("chennai").
                        dropPoint("madurai").
                        build()));

        when(cabMockRepository.save(any())).thenReturn(Cab.builder().cabAgencyId(1).pickUpPoint("salem").dropPoint("trichy").build());
        when(cabAgencyMockRepository.save(any())).thenReturn(CabAgency.builder()
                        .cabs(Arrays.asList(Cab.builder().cabAgencyId(1).pickUpPoint("salem").dropPoint("trichy").build()))
                .build());

        try {
            Assertions.assertEquals("salem",cabAgencySerivce.
                    updateCabPickUpLocationAndDropLocationInCabAgency(1,1,"salem","trichy",500).getCabs().get(0).getPickUpPoint());
        } catch (CabAgencyNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CabNotFoundInCabAgencyException e) {
            throw new RuntimeException(e);
        } catch (CabAgencyMissingInputFieldException e) {
            throw new RuntimeException(e);
        }

        verify(cabMockRepository).save(any());
        verify(cabAgencyMockRepository).save(any());

    }

    @Test
    public void updateCabLocationTestThrowsException()
    {
        when(cabAgencyMockRepository.findById(any())).thenReturn(Optional.ofNullable(CabAgency.builder().cabAgencyId(1).build()));

        when(cabMockRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(CabNotFoundInCabAgencyException.class,
                ()->cabAgencySerivce.updateCabPickUpLocationAndDropLocationInCabAgency(1,1,"abcd","efgh",500));
    }

}
