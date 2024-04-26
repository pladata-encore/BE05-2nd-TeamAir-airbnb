package com.air.room.global.domain.repository;

import com.air.room.global.domain.entity.RoomAmenity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomAmenityRepository
        extends JpaRepository<RoomAmenity, Integer> {
}