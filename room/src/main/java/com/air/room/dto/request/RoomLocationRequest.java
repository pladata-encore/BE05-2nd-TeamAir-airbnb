package com.air.room.dto.request;

import com.air.room.global.domain.entity.Room;
import com.air.room.global.domain.entity.RoomLocation;

import java.math.BigDecimal;
import java.math.BigInteger;

public record RoomLocationRequest (
        BigDecimal locationX,
        BigDecimal locationY
){
    public RoomLocation toEntity(Room room) {
        return RoomLocation.builder()
                .room(room)
                .locationX(locationX)
                .locationY(locationY)
                .build();
    }
}
