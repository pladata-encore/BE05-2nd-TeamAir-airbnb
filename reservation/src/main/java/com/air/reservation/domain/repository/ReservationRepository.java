package com.air.reservation.domain.repository;

import com.air.reservation.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r " +
            "WHERE r.roomId = :roomId AND r.startDate = :startDate AND r.endDate = :endDate")
    Reservation findByRoomIdCheckInCheckOut(@Param("roomId") Long roomId,
                                                  @Param("startDate") String startDate,
                                                  @Param("endDate") String endDate);
    List<Reservation> findByUserId(Integer userId);
    Reservation findByIdAndUserId(Long id, Integer userId);
}
