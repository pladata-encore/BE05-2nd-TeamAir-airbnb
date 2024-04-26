package com.air.room.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SAFETY_SUPPLIES")
@Builder
public class SafetySupply {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "safety_supplies_safety_id_seq")
    @SequenceGenerator(name = "safety_supplies_safety_id_seq", sequenceName = "SAFETY_SUPPLIES_SAFETY_ID_SEQ", allocationSize = 1)
    @Column(name="SAFETY_ID")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @Column(name="SAFETY_FIRE_ALARM", nullable = false)
    private Boolean fireAlarm;

    @Column(name="SAFETY_AID_KIT", nullable = false)
    private Boolean aidKit;

    @Column(name="SAFETY_EXTINGUISHER", nullable = false)
    private Boolean extinguisher;

    @Column(name="SAFETY_CO_ALARM", nullable = false)
    private Boolean coAlarm;

    public void update(SafetySupply req) {
        fireAlarm = req.fireAlarm;
        aidKit = req.aidKit;
        extinguisher = req.extinguisher;
        coAlarm = req.coAlarm;
    }
}
