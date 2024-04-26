package com.air.wishlist.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Favorates")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Favorite {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FAVORATE_ID")
    private Integer id;
    @Column(name="room_id")
    private Integer roomId;
    // 룸 머지되면 JoinColumn 처리 해야됨
    @Column(name="CITY_NAME")
    private String cityName;
    @Column(name="ROOM_TYPE")
    private String roomType;
    @Column(name="STAR_AVG")
    private double starAVG;
    @Column(name ="COMMENT_COUNT")
    private Integer commentCount;
    @Column(name = "ROOM_NAME")
    private String roomName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="wishlist_id")
    private Wishlist wishlist;

}
