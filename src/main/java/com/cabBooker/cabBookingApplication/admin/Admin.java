package com.cabBooker.cabBookingApplication.admin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Admin {

    @Id
    @GeneratedValue
    private Integer adminId;
    private String adminName;
    private String adminEmail;
    private String adminPassword;
    private String adminMobileNumber;


}
