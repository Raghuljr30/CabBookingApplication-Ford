package com.cabBooker.cabBookingApplication.customer;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Long mobileNumber;
    private Boolean currentBookingExist=false;


    @Builder.Default
    @OneToMany(fetch = FetchType.EAGER)
    List<Booking> bookings=new ArrayList<>();

//    public Customer()
//    {
//        super();
//    }

//    public Customer(Integer id, String name, String email, String password, Long mobileNumber, Boolean currentBookingExist, List<Booking> bookings) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.mobileNumber = mobileNumber;
//        this.currentBookingExist = currentBookingExist;
//        this.bookings = bookings;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Long getMobileNumber() {
//        return mobileNumber;
//    }
//
//    public void setMobileNumber(Long mobileNumber) {
//        this.mobileNumber = mobileNumber;
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
//    public Boolean getCurrentBookingExist() {
//        return currentBookingExist;
//    }
//
//    public void setCurrentBookingExist(Boolean currentBookingExist) {
//        this.currentBookingExist = currentBookingExist;
//    }
//
//    @Override
//    public String toString() {
//        return "Customer{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", mobileNumber=" + mobileNumber +
//                ", currentBookingExist=" + currentBookingExist +
//                ", bookings=" + bookings +
//                '}';
//    }
}
