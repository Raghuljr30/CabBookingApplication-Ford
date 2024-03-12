package com.cabBooker.cabBookingApplication.cab;

import com.cabBooker.cabBookingApplication.cabAgency.CabAgency;
import com.cabBooker.cabBookingApplication.driver.Driver;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cab {

    @Id
    @GeneratedValue
    private Integer cabId;
    private String cabModel;
    private Boolean ac;
    private Integer numberOfSeats;
    private Integer vehicleNumber;
    private Boolean availability;
    private String agencyName;

    private Integer cabAgencyId;
    private String pickUpPoint;
    private String dropPoint;
    private Double fair;


    @OneToOne
    private Driver driver;

//    public Cab()
//    {
//        super();
//    }
//
//    public Cab(Integer cabId, String cabModel, Boolean AC, Integer numberOfSeats, Integer vehicleNumber, Boolean availability, String agencyName, Integer cabAgencyId, String pickUpPoint, String dropPoint, Double fair,  Driver driver) {
//        this.cabId = cabId;
//        this.cabModel = cabModel;
//        this.AC = AC;
//        this.numberOfSeats = numberOfSeats;
//        this.vehicleNumber = vehicleNumber;
//        this.availability = availability;
//        this.agencyName = agencyName;
//        this.cabAgencyId = cabAgencyId;
//        this.pickUpPoint = pickUpPoint;
//        this.dropPoint = dropPoint;
//        this.fair = fair;
//        this.driver = driver;
//    }
//
//    public Integer getCabId() {
//        return cabId;
//    }
//
//    public void setCabId(Integer cabId) {
//        this.cabId = cabId;
//    }
//
//    public String getCabModel() {
//        return cabModel;
//    }
//
//    public void setCabModel(String cabModel) {
//        this.cabModel = cabModel;
//    }
//
//    public Boolean getAC() {
//        return AC;
//    }
//
//    public void setAC(Boolean AC) {
//        this.AC = AC;
//    }
//
//    public Integer getNumberOfSeats() {
//        return numberOfSeats;
//    }
//
//    public void setNumberOfSeats(Integer numberOfSeats) {
//        this.numberOfSeats = numberOfSeats;
//    }
//
//    public Integer getVehicleNumber() {
//        return vehicleNumber;
//    }
//
//    public void setVehicleNumber(Integer vehicleNumber) {
//        this.vehicleNumber = vehicleNumber;
//    }
//
//    public Boolean getAvailability() {
//        return availability;
//    }
//
//    public void setAvailability(Boolean availability) {
//        this.availability = availability;
//    }
//
//    public String getAgencyName() {
//        return agencyName;
//    }
//
//    public void setAgencyName(String agencyName) {
//        this.agencyName = agencyName;
//    }
//
//    public String getPickUpPoint() {
//        return pickUpPoint;
//    }
//
//    public void setPickUpPoint(String pickUpPoint) {
//        this.pickUpPoint = pickUpPoint;
//    }
//
//    public String getDropPoint() {
//        return dropPoint;
//    }
//
//    public void setDropPoint(String dropPoint) {
//        this.dropPoint = dropPoint;
//    }
//
//    public Double getFair() {
//        return fair;
//    }
//
//    public void setFair(Double fair) {
//        this.fair = fair;
//    }
//
//    public Driver getDriver() {
//        return driver;
//    }
//
//    public void setDriver(Driver driver) {
//        this.driver = driver;
//    }
//
//    public Integer getDriverAgencyId() {
//        return cabAgencyId;
//    }
//
//    public void setDriverAgencyId(Integer cabAgencyId) {
//        this.cabAgencyId = cabAgencyId;
//    }
//
//
//
//
//    @Override
//    public String toString() {
//        return "Cab{" +
//                "cabId=" + cabId +
//                ", cabModel='" + cabModel + '\'' +
//                ", AC=" + AC +
//                ", numberOfSeats=" + numberOfSeats +
//                ", vehicleNumber=" + vehicleNumber +
//                ", availability=" + availability +
//                ", agencyName='" + agencyName + '\'' +
//                ", cabAgencyId=" + cabAgencyId +
//                ", pickUpPoint='" + pickUpPoint + '\'' +
//                ", dropPoint='" + dropPoint + '\'' +
//                ", fair=" + fair +
//                ", driver=" + driver +
//                '}';
//    }
}
