package com.cabBooker.cabBookingApplication.booking;

import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.payment.Payment;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookingId;
    private String customerName;

    //private Integer customerId;
    private Integer cabNumber;
    @OneToOne
    private Payment payment;
    private LocalDate bookingDate;

    private String pickUpLocation;
    private String dropLocation;

    @ManyToOne
    @JoinColumn(name = "cabId")
    private Cab cab;

    public Booking()
    {
        super();
    }

    public Booking(Integer bookingId, String customerName, Integer cabNumber, Payment payment, LocalDate bookingDate, String pickUpLocation, String dropLocation, Cab cab) {
        super();
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.cabNumber = cabNumber;
        this.payment = payment;
        this.bookingDate = bookingDate;
        this.pickUpLocation = pickUpLocation;
        this.dropLocation = dropLocation;
        this.cab = cab;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCabNumber() {
        return cabNumber;
    }

    public void setCabNumber(Integer cabNumber) {
        this.cabNumber = cabNumber;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }

    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }


    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", customerName='" + customerName + '\'' +
                ", cabNumber=" + cabNumber +
                ", payment=" + payment +
                ", bookingDate=" + bookingDate +
                ", pickUpLocation='" + pickUpLocation + '\'' +
                ", dropLocation='" + dropLocation + '\'' +
                ", cab=" + cab +
                '}';
    }
}
