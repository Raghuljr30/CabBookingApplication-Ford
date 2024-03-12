package com.cabBooker.cabBookingApplication.cabAgency;

import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.driver.Driver;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CabAgency {

    @Id
    @GeneratedValue
    private Integer cabAgencyId;
    private String cabAgencyName;

    private String cabAgencyEmail;
    private String cabAgencyPassword;

    private Long cabAgencyMobileNumber;
//
    @Builder.Default
    @OneToMany(fetch = FetchType.EAGER)
    private List<Cab>cabs=new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.EAGER)
    private List<Driver>drivers=new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.EAGER)
    private List<Booking>bookings=new ArrayList<>();

//    public CabAgency()
//    {
//        super();
//    }
//
//
//    public CabAgency(Integer cabAgencyId, String cabAgencyName, String cabAgencyEmail, String cabAgencyPassword, Long cabAgencyMobileNumber, List<Cab> cabs, List<Driver> drivers, List<Booking> bookings) {
//        super();
//        this.cabAgencyId = cabAgencyId;
//        this.cabAgencyName = cabAgencyName;
//        this.cabAgencyEmail = cabAgencyEmail;
//        this.cabAgencyPassword = cabAgencyPassword;
//        this.cabAgencyMobileNumber = cabAgencyMobileNumber;
//        this.cabs = cabs;
//        this.drivers = drivers;
//        this.bookings = bookings;
//    }
//
//    public Integer getCabAgencyId() {
//        return cabAgencyId;
//    }
//
//    public void setCabAgencyId(Integer cabAgencyId) {
//        this.cabAgencyId = cabAgencyId;
//    }
//
//    public String getCabAgencyName() {
//        return cabAgencyName;
//    }
//
//    public void setCabAgencyName(String cabAgencyName) {
//        this.cabAgencyName = cabAgencyName;
//    }
//
//    public String getCabAgencyEmail() {
//        return cabAgencyEmail;
//    }
//
//    public void setCabAgencyEmail(String cabAgencyEmail) {
//        this.cabAgencyEmail = cabAgencyEmail;
//    }
//
//    public String getCabAgencyPassword() {
//        return cabAgencyPassword;
//    }
//
//    public void setCabAgencyPassword(String cabAgencyPassword) {
//        this.cabAgencyPassword = cabAgencyPassword;
//    }
//
//    public Long getCabAgencyMobileNumber() {
//        return cabAgencyMobileNumber;
//    }
//
//    public void setCabAgencyMobileNumber(Long cabAgencyMobileNumber) {
//        this.cabAgencyMobileNumber = cabAgencyMobileNumber;
//    }
//
//    public List<Cab> getCabs() {
//        return cabs;
//    }
//
//    public void setCabs(List<Cab> cabs) {
//        this.cabs = cabs;
//    }
//
//    public List<Driver> getDrivers() {
//        return drivers;
//    }
//
//    public void setDrivers(List<Driver> drivers) {
//        this.drivers = drivers;
//    }
//
//    public List<Booking> getBookings() {
//        return bookings;
//    }
//
//    public void setBookings(List<Booking> bookings) {
//        this.bookings = bookings;
//    }
//
//    @Override
//    public String toString() {
//        return "CabAgency{" +
//                "cabAgencyId=" + cabAgencyId +
//                ", cabAgencyName='" + cabAgencyName + '\'' +
//                ", cabAgencyEmail='" + cabAgencyEmail + '\'' +
//                ", cabAgencyPassword='" + cabAgencyPassword + '\'' +
//                ", cabAgencyMobileNumber=" + cabAgencyMobileNumber +
//                ", cabs=" + cabs +
//                ", drivers=" + drivers +
//                ", bookings=" + bookings +
//                '}';
//    }
}
