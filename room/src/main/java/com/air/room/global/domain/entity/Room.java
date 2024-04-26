package com.air.room.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ROOMS")
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rooms_room_id_seq")
    @SequenceGenerator(name = "rooms_room_id_seq", sequenceName = "ROOMS_ROOM_ID_SEQ", allocationSize = 1)
    @Column(name = "ROOM_ID")
    private Integer id;

    @Column(name = "USER_ID", nullable = false)
    private Integer userId;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @ManyToOne
    @JoinColumn(name = "CITY_ID")
    private City city;

    @Column(name = "ROOM_NAME", nullable = false)
    private String name;

    @Column(name = "ROOM_DESC", nullable = false)
    private String desc;

    @Column(name = "ROOM_TYPE", nullable = false)
    private Integer type;

    @Column(name = "ROOM_MAX_PEOPLE", nullable = false)
    private Integer maxPeople;

    @Column(name = "ROOM_RESERVE_OPTION", nullable = false)
    private Integer reserveOption;

    @Column(name = "ROOM_BEDROOM_NUM", nullable = false)
    private Integer bedroomNum;

    @Column(name = "ROOM_BED_NUM", nullable = false)
    private Integer bedNum;

    @Column(name = "ROOM_BATHROOM_NUM", nullable = false)
    private Integer bathroomNum;

    @Column(name = "ROOM_PRICE", nullable = false)
    private Integer price;

    @Column(name = "ROOM_CLEANING_PRICE", nullable = false)
    private Integer cleaningPrice;

    @Column(name = "ROOM_CHECK_IN_TIME", nullable = false)
    private Time checkInTime;

    @Column(name = "ROOM_CHECK_OUT_TIME", nullable = false)
    private Time checkOutTime;

    @Column(name = "ROOM_USING_RULE", nullable = false)
    private String usingRule;

    @Column(name = "ROOM_RESERVE_START_AT")
    private LocalDate reserveStartAt;

    @Column(name = "ROOM_RESERVE_END_AT", nullable = false)
    private LocalDate reserveEndAt;

    @Column(name = "ROOM_DISABLE")
    private Boolean isDisable;

    @OneToMany(mappedBy = "room")
    private List<RoomAccessibility> roomAccessibility;
    @OneToMany(mappedBy = "room")
    private List<RoomAmenity> roomAmenities;
    @OneToMany(mappedBy = "room")
    private List<RoomUniqueAmenity> roomUniqueAmenities;

    @OneToOne(mappedBy = "room")
    private RoomLocation roomLocation;

    @OneToOne(mappedBy = "room")
    private SafetySupply safetySupply;


    public void update(Room req) {
        userName = req.getUserName();
        city = req.getCity();
        name = req.getName();
        desc = req.getDesc();
        type = req.getType();
        maxPeople = req.getMaxPeople();
        reserveOption = req.getReserveOption();
        bedroomNum = req.getBedroomNum();
        bedNum = req.getBedNum();
        bathroomNum = req.getBathroomNum();
        price = req.getPrice();
        cleaningPrice = req.getCleaningPrice();
        checkInTime = req.getCheckInTime();
        checkOutTime = req.getCheckOutTime();
        usingRule = req.getUsingRule();
        reserveStartAt = req.getReserveStartAt();
        reserveEndAt = req.getReserveEndAt();
//        roomAccessibility = req.getRoomAccessibility();
//        roomAmenities = req.getRoomAmenities();
//        roomUniqueAmenities = req.getRoomUniqueAmenities();
//        roomLocation = req.getRoomLocation();
//        safetySupply = req.getSafetySupply();
    }

    public void disable() {
        isDisable = true;
    }
}
