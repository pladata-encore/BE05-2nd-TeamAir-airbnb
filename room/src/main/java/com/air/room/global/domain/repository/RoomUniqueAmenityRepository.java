package com.air.room.global.domain.repository;

import com.air.room.global.domain.entity.RoomUniqueAmenity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomUniqueAmenityRepository
        extends JpaRepository<RoomUniqueAmenity, Integer> {
}