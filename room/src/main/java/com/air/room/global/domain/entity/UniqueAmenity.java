package com.air.room.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UNIQUE_AMENITIES")
@Builder
public class UniqueAmenity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unique_amenities_unique_amenity_id_seq")
    @SequenceGenerator(name = "unique_amenities_unique_amenity_id_seq", sequenceName = "UNIQUE_AMENITIES_UNIQUE_AMENITY_ID_SEQ", allocationSize = 1)
    @Column(name = "UNIQUE_AMENITY_ID")
    private Integer id;
    @Column(name = "UNIQUE_AMENITY_NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "uniqueAmenity")
    private List<RoomUniqueAmenity> roomUniqueAmenities;
}