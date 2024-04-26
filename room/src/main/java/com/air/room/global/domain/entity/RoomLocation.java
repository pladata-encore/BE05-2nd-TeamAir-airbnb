package com.air.room.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ROOM_LOCATIONS")
@Builder
public class RoomLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_locations_location_id_seq")
    @SequenceGenerator(name = "room_locations_location_id_seq", sequenceName = "ROOM_LOCATIONS_LOCATION_ID_SEQ", allocationSize = 1)
    @Column(name = "LOCATION_ID")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID", nullable = false)
    private Room room;

    @Column(name = "LOCATION_X", nullable = false)
    private BigDecimal locationX;
    @Column(name = "LOCATION_Y", nullable = false)
    private BigDecimal locationY;

    public void update(RoomLocation req) {
        this.locationX = req.getLocationX();
        this.locationY = req.getLocationY();
    }
}
