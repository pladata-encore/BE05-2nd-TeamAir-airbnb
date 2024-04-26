package com.air.room.global.domain.repository;

import com.air.room.global.domain.entity.Room;
import com.air.room.global.domain.entity.RoomAccessibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository
        extends JpaRepository<Room, Integer> {

    List<Room> findAllByUserId(Integer userId);

    @Query("SELECT r " +
            "FROM Room r " +
            "JOIN FETCH r.city " +
            "JOIN FETCH r.roomLocation " +
            "JOIN FETCH r.safetySupply " +
            "LEFT JOIN r.roomAccessibility r_ac " +
            "LEFT JOIN r_ac.accessibility " +
            "LEFT JOIN r.roomAmenities r_am " +
            "LEFT JOIN r_am.amenity " +
            "LEFT JOIN r.roomUniqueAmenities r_ua " +
            "LEFT JOIN r_ua.uniqueAmenity")
    List<Room> findAllRoomOnJPQL();

    @Query("SELECT r " +
            "FROM Room r " +
            "JOIN FETCH r.city " +
            "JOIN FETCH r.roomLocation " +
            "JOIN FETCH r.safetySupply " +
            "LEFT JOIN r.roomAccessibility r_ac " +
            "LEFT JOIN r_ac.accessibility " +
            "LEFT JOIN r.roomAmenities r_am " +
            "LEFT JOIN r_am.amenity " +
            "LEFT JOIN r.roomUniqueAmenities r_ua " +
            "LEFT JOIN r_ua.uniqueAmenity " +
            "WHERE r.id = :roomId")
    Room findRoomByRoomIdOnJPQL(@Param("roomId") Integer roomId);

    @Query("SELECT r " +
            "FROM Room r " +
            "JOIN FETCH r.city " +
            "JOIN FETCH r.roomLocation " +
            "JOIN FETCH r.safetySupply " +
            "LEFT JOIN r.roomAccessibility r_ac " +
            "LEFT JOIN r_ac.accessibility " +
            "LEFT JOIN r.roomAmenities r_am " +
            "LEFT JOIN r_am.amenity " +
            "LEFT JOIN r.roomUniqueAmenities r_ua " +
            "LEFT JOIN r_ua.uniqueAmenity " +
            "WHERE r.userId = :userId")
    List<Room> findAllRoomByUserIdOnJPQL(@Param("userId") Integer userId);
}
