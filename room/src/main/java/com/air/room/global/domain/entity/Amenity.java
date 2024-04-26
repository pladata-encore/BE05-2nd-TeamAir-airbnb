package com.air.room.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AMENITIES")
@Builder
public class Amenity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "amenities_amenity_id_seq")
    @SequenceGenerator(name = "amenities_amenity_id_seq", sequenceName = "AMENITIES_AMENITY_ID_SEQ", allocationSize = 1)
    @Column(name = "AMENITY_ID")
    private Integer id;
    @Column(name = "AMENITY_NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "amenity")
    private List<RoomAmenity> roomAmenities;
}
