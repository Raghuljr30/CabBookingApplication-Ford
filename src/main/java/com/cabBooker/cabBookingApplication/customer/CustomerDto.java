package com.cabBooker.cabBookingApplication.customer;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class CustomerDto {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Long mobileNumber;
    private Boolean currentBookingExist=false;
    public CustomerDto()
    {

    }

    public CustomerDto(Integer id, String name, String email, String password, Long mobileNumber, Boolean currentBookingExist) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.currentBookingExist = currentBookingExist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Boolean getCurrentBookingExist() {
        return currentBookingExist;
    }

    public void setCurrentBookingExist(Boolean currentBookingExist) {
        this.currentBookingExist = currentBookingExist;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", currentBookingExist=" + currentBookingExist +
                '}';
    }
}
