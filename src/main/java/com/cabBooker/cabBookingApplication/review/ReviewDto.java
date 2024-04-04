package com.cabBooker.cabBookingApplication.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    public Integer reviewedBy;
    public Integer bookingId;
    public String review;
}
