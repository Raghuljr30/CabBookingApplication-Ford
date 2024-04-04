package com.cabBooker.cabBookingApplication.driver;


import com.cabBooker.cabBookingApplication.review.Review;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Driver {


    @Id
    @GeneratedValue
    private Integer driverId;
    private String driverName;
    private String driverEmail;
    private String driverPassword;
    private Long driverMobileNumber;
    private Integer licenseNumber;

    private Integer driverVehicleNumber;

    private String driverAgencyName;
    private Integer  driverAgencyId;
    private  String reviews;

    @OneToMany(fetch = FetchType.EAGER)
    private  List<Review>review=new ArrayList<>();

//    public List<Review> getReview() {
//        return review;
//    }
//
//    public void setReview(List<Review> review) {
//        this.review = review;
//    }
//
//    public Driver()
//    {
//        super();
//    }
//
//    public Driver(Integer driverId, String driverName, String driverEmail, String driverPassword, Long driverMobileNumber, Integer licenseNumber, Integer driverVehicleNumber, String driverAgencyName, Integer driverAgencyId, String reviews) {
//        this.driverId = driverId;
//        this.driverName = driverName;
//        this.driverEmail = driverEmail;
//        this.driverPassword = driverPassword;
//        this.driverMobileNumber = driverMobileNumber;
//        this.licenseNumber = licenseNumber;
//        this.driverVehicleNumber = driverVehicleNumber;
//        this.driverAgencyName = driverAgencyName;
//        this.driverAgencyId = driverAgencyId;
//        this.reviews = reviews;
//    }
//
//    public Integer getDriverAgencyId() {
//        return driverAgencyId;
//    }
//
//    public void setDriverAgencyId(Integer driverAgencyId) {
//        this.driverAgencyId = driverAgencyId;
//    }
//
//    public Integer getDriverId() {
//        return driverId;
//    }
//
//    public void setDriverId(Integer driverId) {
//        this.driverId = driverId;
//    }
//
//    public String getDriverName() {
//        return driverName;
//    }
//
//    public void setDriverName(String driverName) {
//        this.driverName = driverName;
//    }
//
//    public String getDriverEmail() {
//        return driverEmail;
//    }
//
//    public void setDriverEmail(String driverEmail) {
//        this.driverEmail = driverEmail;
//    }
//
//    public String getDriverPassword() {
//        return driverPassword;
//    }
//
//    public void setDriverPassword(String driverPassword) {
//        this.driverPassword = driverPassword;
//    }
//
//    public Long getDriverMobileNumber() {
//        return driverMobileNumber;
//    }
//
//    public void setDriverMobileNumber(Long driverMobileNumber) {
//        this.driverMobileNumber = driverMobileNumber;
//    }
//
//    public Integer getLicenseNumber() {
//        return licenseNumber;
//    }
//
//    public void setLicenseNumber(Integer licenseNumber) {
//        this.licenseNumber = licenseNumber;
//    }
//
////    public String getReviews() {
////        return reviews;
////    }
////
////    public List<Review>  setReviews(List<Review> reviews) {
////        this.reviews = reviews;
////    }
//
//    public Integer getDriverVehicleNumber() {
//        return driverVehicleNumber;
//    }
//
//    public void setDriverVehicleNumber(Integer driverVehicleNumber) {
//        this.driverVehicleNumber = driverVehicleNumber;
//    }
//
//    public String getDriverAgencyName() {
//        return driverAgencyName;
//    }
//
//    public void setDriverAgencyName(String driverAgencyName) {
//        this.driverAgencyName = driverAgencyName;
//    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
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
