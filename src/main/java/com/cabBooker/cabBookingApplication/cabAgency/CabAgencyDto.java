package com.cabBooker.cabBookingApplication.cabAgency;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CabAgencyDto {


        @NotBlank(message = "Cab Agency Name is required")
        private String cabAgencyName;

        @NotBlank(message = "Cab Agency Email is required")
        @Email(message = "Invalid email format")
        private String cabAgencyEmail;

        @NotBlank(message = "Cab Agency Password is required")
        @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
        private String cabAgencyPassword;

        @NotNull(message = "Cab Agency Mobile Number is required")
//        @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number format. Mobile number must be 10 digits.")
        private Long cabAgencyMobileNumber;

}
