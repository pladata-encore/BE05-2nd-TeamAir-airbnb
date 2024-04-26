package com.air.reservation.domain.dto.response;

import com.air.reservation.domain.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

public record ReadResponse(
        Long id, Integer guest_count, String check_in, String check_out,
        String message, Integer total_money, Long roomId
) {
//    public static from(Reservation reservation) {
//        List<Reservation> reservations =
//    }
}
