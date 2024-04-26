package com.air.reservation.controller;

import com.air.reservation.config.JwtTokenUtils;
import com.air.reservation.config.TokenInfo;
import com.air.reservation.domain.dto.request.CreateDateRequest;
import com.air.reservation.domain.dto.request.CreateRequest;
import com.air.reservation.domain.dto.request.UpdateRequest;
import com.air.reservation.domain.dto.response.ReadResponse;
import com.air.reservation.domain.entity.Reservation;
import com.air.reservation.domain.entity.ReservationDate;
import com.air.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reserve")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    private final JwtTokenUtils jwtTokenUtils;

    @GetMapping("/token")
    public String testToken() {
        return jwtTokenUtils.createToken(1,"qwer");
    }
    @GetMapping("/token1")
    public String test2Token() {
        return jwtTokenUtils.createToken(2,"qwer");
    }

    @PostMapping("/create/{id}")
    public void createReservation(@RequestBody CreateRequest req,
                                  @PathVariable("id") Long roomId,
                                  @RequestParam("check_in") String startDate,
                                  @RequestParam("check_out") String endDate) {
        reservationService.create(req, roomId, startDate, endDate);
    }

    @PostMapping("/date/create/{id}")
    public List<ReservationDate> createReservationDate(@RequestBody CreateDateRequest req,
                                                       @PathVariable("id") Long roomId,
                                                       @RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtTokenUtils.parseToken(token);
        return reservationService.createReservationDate(req, roomId, tokenInfo);
    }

    @GetMapping("/user/reservation")
    public List<ReadResponse> getALlReservationToUser(@RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtTokenUtils.parseToken(token);
        return reservationService.getALlReservationToUser(tokenInfo);
    }

    @GetMapping("/{id}")
    public ReadResponse getOneReservation(@PathVariable("id") Long id) {
        return reservationService.getReservation(id);
    }

    @PutMapping("/update/{id}")
    public void updateReservation(@PathVariable("id") Long id,
                                  @RequestBody UpdateRequest req,
                                  @RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtTokenUtils.parseToken(token);
        reservationService.update(id, req, tokenInfo);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReservation(@PathVariable("id") Long id,
                                  @RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtTokenUtils.parseToken(token);
        reservationService.delete(id, tokenInfo);
    }
}
