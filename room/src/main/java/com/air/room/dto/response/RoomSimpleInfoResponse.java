package com.air.room.dto.response;

import com.air.room.global.domain.dto.RoomLocationDto;
import com.air.room.global.domain.entity.City;
import com.air.room.global.domain.entity.Room;

import java.time.LocalDate;

public record RoomSimpleInfoResponse(
        Integer id,
        City city,
        String name,
        LocalDate reserveStartAt,
        LocalDate reserveEndAt,
        Integer price,
        RoomLocationDto location
) {
    public static RoomSimpleInfoResponse from(Room room) {
        RoomLocationDto location = room.getRoomLocation() == null ? null : new RoomLocationDto(
                room.getRoomLocation().getLocationX(),
                room.getRoomLocation().getLocationY()
        );
        return new RoomSimpleInfoResponse(
                room.getId(),
                room.getCity(),
                room.getName(),
                room.getReserveStartAt(),
                room.getReserveEndAt(),
                room.getPrice(),
                location
        );
    }
}
