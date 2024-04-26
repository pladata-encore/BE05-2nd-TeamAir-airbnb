package com.air.room.global.domain.repository;

import com.air.room.global.domain.entity.SafetySupply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SafetySupplyRepository
        extends JpaRepository<SafetySupply, Integer> {

    SafetySupply findByRoomId(Integer roomId);
}