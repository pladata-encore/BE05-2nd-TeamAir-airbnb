package com.air.room.global.domain.dto;

import java.math.BigDecimal;

public record RoomLocationDto (
        BigDecimal locationX,
        BigDecimal locationY
){
}
