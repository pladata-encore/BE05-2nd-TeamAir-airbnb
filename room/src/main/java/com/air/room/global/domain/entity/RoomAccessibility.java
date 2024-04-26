package com.air.room.global.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ROOM_ACCESSIBILITES")
@Builder
public class RoomAccessibility {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_accessibilites_room_accessibility_id_seq")
    @SequenceGenerator(name = "room_accessibilites_room_accessibility_id_seq", sequenceName = "ROOM_ACCESSIBILITES_ROOM_ACCESSIBILITY_ID_SEQ", allocationSize = 1)
    @Column(name = "ROOM_ACCESSIBILITY_ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCESSIBILITY_ID", nullable = false)
    private Accessibility accessibility;
}
