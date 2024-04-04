package com.example.CabBookingProject.driver;

import com.example.CabBookingProject.review.Review;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Driver {

    @Id
    @GeneratedValue
    private Integer driverId;
    private String driverName;
    private String driverEmail;
    private String driverPassword;
    private Long driverMobileNumber;
    private Integer licenseNumber;

    private String driverAgencyName;

    @OneToMany
    private List<Review>reviews=new ArrayList<>();

    public Driver()
    {
        super();
    }
    public Driver(Integer driverId, String driverName, String driverEmail, String driverPassword, Long driverMobileNumber, Integer licenseNumber, List<Review> reviews) {
        super();
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverEmail = driverEmail;
        this.driverPassword = driverPassword;
        this.driverMobileNumber = driverMobileNumber;
        this.licenseNumber = licenseNumber;
        this.reviews = reviews;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
                ", driverName='" + driverName + '\'' +
                ", driverEmail='" + driverEmail + '\'' +
                ", driverPassword='" + driverPassword + '\'' +
                ", driverMobileNumber=" + driverMobileNumber +
                ", licenseNumber=" + licenseNumber +
                ", reviews=" + reviews +
                '}';
    }
}
