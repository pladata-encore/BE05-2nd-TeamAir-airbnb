package com.air.reservation.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @NoArgsConstructor
@AllArgsConstructor
@Table @Builder
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;
    @Column(name = "guest_count")
    @Setter
    private Integer guestCount;
    @Column(name = "check_in")
    @Setter
    private String startDate;
    @Column(name = "check_out")
    @Setter
    private String endDate;
    @Column(name = "message")
    @Setter
    private String message;
    @Column()
    @Setter
    private Integer total_money;
    @Column()
    @Setter
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation")
    @Setter
    private List<ReservationDate> reservationDates;
    @Column(name = "room_id")
    private Long roomId;
    @Column(name = "user_id")
    private Integer userId;
}
