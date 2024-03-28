package com.cabBooker.cabBookingApplication.driver;

import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.review.Review;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

public class DriverDto {

    @Id
    @GeneratedValue
 @NotNull(message = "Driver name cannot be null")
  @NotBlank(message = "Driver name cannot be blank")
    private String driverName;
   @Email(message="Email must be in correct format, Eg. ford@gmail.com")
    private String driverEmail;
    private String driverPassword;
    private Long driverMobileNumber;
    @NotNull(message = "License Number cannot be null")
    private Integer licenseNumber;
    @NotNull(message = "Vehicle Number cannot be null")
    private Integer driverVehicleNumber;
    @NotNull(message = "Cab Agency cannot be null")
    @NotBlank(message = "Cab Agency cannot be blank")
    private String driverAgencyName;
    private Integer driverAgencyId;


    public DriverDto()
    {
        super();
    }

    public DriverDto(String driverName, String driverEmail, String driverPassword, Long driverMobileNumber, Integer licenseNumber,  Integer driverVehicleNumber, String driverAgencyName,Integer driverAgencyId) {

        this.driverName = driverName;
        this.driverEmail = driverEmail;
        this.driverPassword = driverPassword;
        this.driverMobileNumber = driverMobileNumber;
        this.licenseNumber = licenseNumber;

        this.driverVehicleNumber = driverVehicleNumber;
        this.driverAgencyName = driverAgencyName;
        this.driverAgencyId = driverAgencyId;


    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public String getDriverPassword() {
        return driverPassword;
    }

    public void setDriverPassword(String driverPassword) {
        this.driverPassword = driverPassword;
    }

    public Long getDriverMobileNumber() {
        return driverMobileNumber;
    }

    public void setDriverMobileNumber(Long driverMobileNumber) {
        this.driverMobileNumber = driverMobileNumber;
    }

    public Integer getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(Integer licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
    public Integer getDriverVehicleNumber() {
        return driverVehicleNumber;
    }

    public void setDriverVehicleNumber(Integer driverVehicleNumber) {
        this.driverVehicleNumber = driverVehicleNumber;
    }

    public String getDriverAgencyName() {
        return driverAgencyName;
    }

    public void setDriverAgencyName(String driverAgencyName) {
        this.driverAgencyName = driverAgencyName;
    }

    public Integer getDriverAgencyId() {
        return driverAgencyId;
    }
    public void setDriverAgencyId(Integer driverAgencyId) {
        this.driverAgencyId = driverAgencyId;
    }


    @Override
    public String toString() {
        return "Driver{" +
                ", driverName='" + driverName + '\'' +
                ", driverEmail='" + driverEmail + '\'' +
                ", driverPassword='" + driverPassword + '\'' +
                ", driverMobileNumber=" + driverMobileNumber +
                ", licenseNumber=" + licenseNumber +
                ", driverVehicleNumber=" + driverVehicleNumber +
                ", driverAgencyName='" + driverAgencyName + '\'' +
                ", driverAgencyId='" + driverAgencyId + '\'' +
                '}';
    }
}
