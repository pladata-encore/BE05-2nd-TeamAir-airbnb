package com.air.room.global.domain.dto;

public record SafetySupplyDto(
        Boolean fireAlarm,
        Boolean aidKit,
        Boolean extinguisher,
        Boolean coAlarm
) {
}
