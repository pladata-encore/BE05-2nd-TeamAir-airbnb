package com.air.room.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record SearchRoomDto (
        Integer cityCode,
        Integer roomType,
        Integer personNum,
        Integer roomReserve,
        Integer bedroomNum,
        Integer bedNum,
        Integer bathroomNum,
        Integer minPrice,
        Integer maxPrice,
        LocalDate reserveStart,
        LocalDate reserveEnd,
        Integer[] amenities,
        Integer[] uniqueAmenities,
        Integer[] accessibilites,
        Boolean fireAlarm,
        Boolean aidKit,
        Boolean extinguisher,
        Boolean coAlarm
){
}
