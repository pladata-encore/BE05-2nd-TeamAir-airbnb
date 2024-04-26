package com.air.reservation.domain.dto.request;

import com.air.reservation.domain.entity.Reservation;
import com.air.reservation.domain.entity.ReservationDate;

import java.time.LocalDate;
import java.util.List;

public record UpdateRequest(
        Integer guestCount, String startDate, String endDate,
        String message, Integer totalMoney
) {
    public Reservation toEntity(List<ReservationDate> reservationDates) {
        return Reservation.builder()
                .guestCount(guestCount)
                .startDate(startDate)
                .endDate(endDate)
                .message(message)
                .total_money(totalMoney)
                .reservationDates(reservationDates)
                .build();
    }
}
