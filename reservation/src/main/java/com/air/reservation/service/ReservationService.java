package com.air.reservation.service;


import com.air.reservation.config.TokenInfo;
import com.air.reservation.domain.dto.request.CreateDateRequest;
import com.air.reservation.domain.dto.request.CreateRequest;
import com.air.reservation.domain.dto.request.UpdateRequest;
import com.air.reservation.domain.dto.response.ReadResponse;
import com.air.reservation.domain.entity.ReservationDate;

import java.util.List;

public interface ReservationService {
    void create(CreateRequest req, Long roomId, String startDate, String endDate);
    List<ReadResponse> getALlReservationToUser(TokenInfo tokenInfo);
    List<ReservationDate> createReservationDate(CreateDateRequest req, Long roomId, TokenInfo tokenInfo);
    ReadResponse getReservation(Long id);
    void update(Long id, UpdateRequest req, TokenInfo tokenInfo);
    void delete(Long id, TokenInfo tokenInfo);
}
