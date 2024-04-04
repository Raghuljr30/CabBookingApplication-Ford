package com.cabBooker.cabBookingApplication.customer;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.Name;
import lombok.*;
import org.aspectj.bridge.IMessage;
import org.hibernate.sql.results.graph.Fetch;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue

    private Integer id;
    //not blank
    //pattern
    @NotNull(message="name should not be null")
    @NotBlank(message = "name should not be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only alphabetic characters")
    private String name;
    @NotNull(message = "email should not be null")
    @NotBlank(message = "email should not be blank")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String email;
    @NotNull(message = "password should not be null")
    @NotBlank(message = "password should not be blank")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{3,}$", message = "Password must contain at least one letter and one digit, and be at least 3 characters long")
    private String password;

    private Long mobileNumber;
    private Boolean currentBookingExist;

    @Builder.Default
    @OneToMany(fetch = FetchType.EAGER)

    List<Booking> bookings=new ArrayList<>();

//
//    public Customer()
//    {
//        super();
//    }
//
//    public Customer(Integer id, String name, String email, String password, Long mobileNumber, List<Booking> bookings) {
//        super();
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.mobileNumber = mobileNumber;
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
//    @Override
//    public String toString() {
//        return "Customer{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", mobileNumber=" + mobileNumber +
//                ", bookings=" + bookings +
//                '}';
//    }
}
