package com.air.room.dto.response;

import com.air.room.global.domain.dto.*;
import com.air.room.global.domain.entity.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public record RoomAllInfoResponse(
        Integer id,
        Integer userId,
        String userName,
        City city,
        String name,
        String desc,
        Integer type,
        Integer maxPeople,
        Integer reserveOption,
        Integer bedroomNum,
        Integer bedNum,
        Integer bathroomNum,
        Integer price,
        Integer cleaningPrice,
        Time checkInTime,
        Time checkOutTime,
        String usingRule,
        LocalDate reserveStartAt,
        LocalDate reserveEndAt,
        List<RoomAccessibilityDto> accessibility,
        List<RoomAmenityDto> amenity,
        List<RoomUniqueAmenityDto> uniqueAmenity,
        RoomLocationDto location,
        SafetySupplyDto safetySupply
){
    public static RoomAllInfoResponse from(Room room) {
        List<RoomAccessibilityDto> accessibility = room.getRoomAccessibility().isEmpty() ? null :
                room.getRoomAccessibility().stream().map(
                roomAccessibility -> new RoomAccessibilityDto(
                        roomAccessibility.getId(),
                        roomAccessibility.getAccessibility().getName())
        ).toList();
        List<RoomAmenityDto> amenity = room.getRoomAmenities().isEmpty() ? null :
                room.getRoomAmenities().stream().map(
                roomAccessibility -> new RoomAmenityDto(
                        roomAccessibility.getId(),
                        roomAccessibility.getAmenity().getName())
        ).toList();
        List<RoomUniqueAmenityDto> uniqueAmenity = room.getRoomUniqueAmenities().isEmpty() ? null :
                room.getRoomUniqueAmenities().stream().map(
                roomAccessibility -> new RoomUniqueAmenityDto(
                        roomAccessibility.getId(),
                        roomAccessibility.getUniqueAmenity().getName())
        ).toList();
        RoomLocationDto location = room.getRoomLocation() == null ? null : new RoomLocationDto(
                room.getRoomLocation().getLocationX(),
                room.getRoomLocation().getLocationY()
        );
        SafetySupplyDto safetySupply = room.getSafetySupply() == null ? null : new SafetySupplyDto(
                room.getSafetySupply().getFireAlarm(),
                room.getSafetySupply().getAidKit(),
                room.getSafetySupply().getExtinguisher(),
                room.getSafetySupply().getCoAlarm()
        );
        return new RoomAllInfoResponse(
                room.getId(),
                room.getUserId(),
                room.getUserName(),
                room.getCity(),
                room.getName(),
                room.getDesc(),
                room.getType(),
                room.getMaxPeople(),
                room.getReserveOption(),
                room.getBedroomNum(),
                room.getBedNum(),
                room.getBathroomNum(),
                room.getPrice(),
                room.getCleaningPrice(),
                room.getCheckInTime(),
                room.getCheckOutTime(),
                room.getUsingRule(),
                room.getReserveStartAt(),
                room.getReserveEndAt(),
                accessibility,
                amenity,
                uniqueAmenity,
                location,
                safetySupply
        );
    }
}
