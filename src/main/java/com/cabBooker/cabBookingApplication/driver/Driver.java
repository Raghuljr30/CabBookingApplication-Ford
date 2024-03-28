package com.cabBooker.cabBookingApplication.driver;

import com.cabBooker.cabBookingApplication.review.Review;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;


@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Driver {

    @Id
    @GeneratedValue
    private Integer driverId;
    @NotNull(message = "Driver name cannot be null")
    @NotBlank(message = "Driver name cannot be blank")
    private String driverName;
    @Email(message="Email must be in correct format, Eg. ford@gmail.com")
    private String driverEmail;
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    private String driverPassword;
    @NotNull(message = "Mobile Number cannot be null")
    @NotBlank(message = "Mobile Number cannot be blank")
    private Long driverMobileNumber;
    @NotNull(message = "License Number cannot be null")
    @NotBlank(message = "License Number cannot be blank")
    private Integer licenseNumber;
    @NotNull(message = "Vehicle Number cannot be null")
    @NotBlank(message = "Vehicle Number cannot be blank")
    private Integer driverVehicleNumber;
    @NotNull(message = "Cab Agency cannot be null")
    @NotBlank(message = "Cab Agency cannot be blank")
    private String driverAgencyName;
    private Integer driverAgencyId;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Review> reviews = new ArrayList<>();
}
