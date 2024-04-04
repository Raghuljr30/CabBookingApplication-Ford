package com.cabBooker.cabBookingApplication.cab;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity
public class Cabdto {
    @Id
    @GeneratedValue
    private Integer cabId;
    private String cabModel;
    private Boolean AC;
    private Integer numberOfSeats;
    private Integer vehicleNumber;
    private Boolean availability;
    private String agencyName;
    private String pickUpPoint;
    private String dropPoint;
    private Double fair;
    private Integer CabAgencyId;

    public Cabdto() {
    }

    public Cabdto(Integer cabId, String cabModel, Boolean AC, Integer numberOfSeats, Integer vehicleNumber, Boolean availability, String agencyName, String pickUpPoint, String dropPoint, Double fair, Integer cabAgencyId) {
        this.cabId = cabId;
        this.cabModel = cabModel;
        this.AC = AC;
        this.numberOfSeats = numberOfSeats;
        this.vehicleNumber = vehicleNumber;
        this.availability = availability;
        this.agencyName = agencyName;
        this.pickUpPoint = pickUpPoint;
        this.dropPoint = dropPoint;
        this.fair = fair;
        CabAgencyId = cabAgencyId;
    }

    public Integer getCabId() {
        return cabId;
    }

    public void setCabId(Integer cabId) {
        this.cabId = cabId;
    }

    public String getCabModel() {
        return cabModel;
    }

    public void setCabModel(String cabModel) {
        this.cabModel = cabModel;
    }

    public Boolean getAC() {
        return AC;
    }

    public void setAC(Boolean AC) {
        this.AC = AC;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(Integer vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getPickUpPoint() {
        return pickUpPoint;
    }

    public void setPickUpPoint(String pickUpPoint) {
        this.pickUpPoint = pickUpPoint;
    }

    public String getDropPoint() {
        return dropPoint;
    }

    public void setDropPoint(String dropPoint) {
        this.dropPoint = dropPoint;
    }

    public Double getFair() {
        return fair;
    }

    public void setFair(Double fair) {
        this.fair = fair;
    }

    public Integer getCabAgencyId() {
        return CabAgencyId;
    }

    public void setCabAgencyId(Integer cabAgencyId) {
        CabAgencyId = cabAgencyId;
    }

    @Override
    public String toString() {
        return "Cabdto{" +
                "cabId=" + cabId +
                ", cabModel='" + cabModel + '\'' +
                ", AC=" + AC +
                ", numberOfSeats=" + numberOfSeats +
                ", vehicleNumber=" + vehicleNumber +
                ", availability=" + availability +
                ", agencyName='" + agencyName + '\'' +
                ", pickUpPoint='" + pickUpPoint + '\'' +
                ", dropPoint='" + dropPoint + '\'' +
                ", fair=" + fair +
                ", CabAgencyId=" + CabAgencyId +
                '}';
    }
}
