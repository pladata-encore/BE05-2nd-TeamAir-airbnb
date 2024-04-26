package com.air.room.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ROOM_UNIQUE_AMENITIES")
@Builder
public class RoomUniqueAmenity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_unique_amenities_room_unique_amenity_id_seq")
    @SequenceGenerator(name = "room_unique_amenities_room_unique_amenity_id_seq", sequenceName = "ROOM_UNIQUE_AMENITIES_ROOM_UNIQUE_AMENITY_ID_SEQ", allocationSize = 1)
    @Column(name = "ROOM_UNIQUE_AMENITY_ID", columnDefinition = "serial")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNIQUE_AMENITY_ID", nullable = false)
    private UniqueAmenity uniqueAmenity;
}
