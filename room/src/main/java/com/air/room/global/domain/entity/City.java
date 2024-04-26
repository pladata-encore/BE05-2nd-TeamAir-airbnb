package com.air.room.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CITES")
@Builder
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cites_city_id_seq")
    @SequenceGenerator(name = "cites_city_id_seq", sequenceName = "CITES_CITY_ID_SEQ", allocationSize = 1)
    @Column(name = "CITY_ID")
    private Integer id;
    @Column(name = "CITY_CODE", nullable = false)
    private Integer code;
    @Column(name = "CITY_NAME", nullable = false)
    private String name;
}
