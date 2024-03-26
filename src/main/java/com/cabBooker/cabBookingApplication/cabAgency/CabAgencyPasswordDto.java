package com.cabBooker.cabBookingApplication.cabAgency;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CabAgencyPasswordDto {


    private String newPassword;
    private String confirmNewPassword;
}
