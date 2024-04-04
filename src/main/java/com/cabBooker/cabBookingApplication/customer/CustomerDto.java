package com.cabBooker.cabBookingApplication.customer;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerDto {
    private Integer id;
    private Long mobileNumber;
    private String password;

    public CustomerDto(Integer id, String password) {

        this.id = id;
        this.password = password;
    }

    public CustomerDto(Integer id, Long mobileNumber) {
        this.id = id;
        this.mobileNumber = mobileNumber;
    }
}
