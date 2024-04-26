package com.air.reservation.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Table @Builder @Getter
public class ReservationDate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;
    @Column(name = "year")
    @Setter
    private String year;
    @Column(name = "month")
    @Setter
    private String month;
    @Column(name = "day")
    @Setter
    private String day;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}

