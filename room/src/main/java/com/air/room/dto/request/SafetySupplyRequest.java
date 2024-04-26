package com.air.room.dto.request;

import com.air.room.global.domain.entity.Room;
import com.air.room.global.domain.entity.SafetySupply;

public record SafetySupplyRequest(
        Boolean fireAlarm,
        Boolean aidKit,
        Boolean extinguisher,
        Boolean coAlarm
) {
    public SafetySupply toEntity(Room room) {
        return SafetySupply.builder()
                .room(room)
                .fireAlarm(fireAlarm)
                .aidKit(aidKit)
                .extinguisher(extinguisher)
                .coAlarm(coAlarm)
                .build();
    }
}
