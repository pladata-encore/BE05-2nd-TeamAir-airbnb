package com.air.room.global.domain.repository;

import com.air.room.global.domain.entity.RoomAccessibility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomAccessibilityRepository
        extends JpaRepository<RoomAccessibility, Integer> {
}