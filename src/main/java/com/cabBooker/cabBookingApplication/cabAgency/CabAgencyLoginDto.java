package com.cabBooker.cabBookingApplication.cabAgency;

import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.driver.Driver;
import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CabAgencyLoginDto {


    @NotNull(message = "CabAgency ID cannot be null")
    private Integer cabAgencyId;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String cabAgencyEmail;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String cabAgencyPassword;


}