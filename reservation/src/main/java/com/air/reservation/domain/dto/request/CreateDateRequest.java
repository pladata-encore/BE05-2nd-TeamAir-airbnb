package com.air.reservation.domain.dto.request;

import com.air.reservation.domain.entity.Reservation;
import com.air.reservation.domain.entity.ReservationDate;

import java.time.LocalDate;
import java.util.List;

public record CreateDateRequest(
        String checkIn, String checkOut
) {

}
