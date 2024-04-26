package com.air.reservation.domain.dto.request;

import com.air.reservation.domain.entity.Reservation;
import com.air.reservation.domain.entity.ReservationDate;

import java.time.LocalDate;
import java.util.List;

public record CreateRequest(
        Integer guestCount, String message, Integer totalMoney, String status
) {
    // check_in, check_out 타입 지정 필요
    public Reservation toEntity(List<ReservationDate> reservationDates) {
        return Reservation.builder()
                .guestCount(guestCount)
                .message(message)
                .total_money(totalMoney)
                .status(status)
                .reservationDates(reservationDates)
                .build();
    }
}
