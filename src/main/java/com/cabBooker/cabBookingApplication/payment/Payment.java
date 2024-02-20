package com.cabBooker.cabBookingApplication.payment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Payment {

    @Id
    @GeneratedValue
    private Integer paymentId;
    private String paymentType;
    private String paidBy;
    private Double paidAmount;

    public Payment()
    {
        super();
    }
    public Payment(Integer paymentId, String paymentType, String paidBy, Double paidAmount) {
        super();
        this.paymentId = paymentId;
        this.paymentType = paymentType;
        this.paidBy = paidBy;
        this.paidAmount = paidAmount;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", paymentType='" + paymentType + '\'' +
                ", paidBy='" + paidBy + '\'' +
                ", paidAmount=" + paidAmount +
                '}';
    }
}
