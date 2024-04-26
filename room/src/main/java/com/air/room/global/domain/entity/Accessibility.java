package com.air.room.global.domain.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ACCESSIBILITES")
@Builder
public class Accessibility {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accessibilites_accessibility_id_seq")
    @SequenceGenerator(name = "accessibilites_accessibility_id_seq", sequenceName = "ACCESSIBILITES_ACCESSIBILITY_ID_SEQ", allocationSize = 1)
    @Column(name = "ACCESSIBILITY_ID")
    private Integer id;
    @Column(name = "ACCESSIBILITY_NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "accessibility")
    private List<RoomAccessibility> roomAccessibilities;
}
