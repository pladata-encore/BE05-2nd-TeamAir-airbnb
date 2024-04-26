package com.air.room.dto.request;

import com.air.room.global.domain.entity.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record RoomRequest(
        //userid, username은 토큰으로
        Integer cityId,
        String name,
        String desc,
        Integer type,
        Integer maxPeople,
        Integer reserveOption,
        Integer bedroomNum,
        Integer bedNum,
        Integer bathroomNum,
        Integer price,
        Integer cleaningPrice,
        Integer checkInTime,
        Integer checkOutTime,
        String usingRule,
        String reserveStartAt,
        String reserveEndAt,
        Integer[] accessibility,
        Integer[] amenities,
        Integer[] uniqueAmenities,
        RoomLocationRequest roomLocationRequest,
        SafetySupplyRequest safetySupplyRequest
) {
    public Room toEntity(Integer userId, String userName) {
        return new Room(
                null,
                userId,
                userName,
                City.builder().id(cityId).build(),
                name,
                desc,
                type,
                maxPeople,
                reserveOption,
                bedroomNum,
                bedNum,
                bathroomNum,
                price,
                cleaningPrice,
                new Time(checkInTime),
                new Time(checkOutTime),
                usingRule,
                LocalDate.parse(reserveStartAt, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse(reserveEndAt, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                false,
                null,
                null,
                null,
                null,
                null
        );
    }

    public Room toEntity(Integer roomId, Integer userId, String userName) {
        return new Room(
                roomId,
                userId,
                userName,
                City.builder().id(cityId).build(),
                name,
                desc,
                type,
                maxPeople,
                reserveOption,
                bedroomNum,
                bedNum,
                bathroomNum,
                price,
                cleaningPrice,
                new Time(checkInTime),
                new Time(checkOutTime),
                usingRule,
                LocalDate.parse(reserveStartAt, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse(reserveEndAt, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                false,
                null,
                null,
                null,
                null,
                null
        );
    }

    public List<RoomAccessibility> getRoomAccessibilityList(Room room){
        return Arrays.stream(this.accessibility).map(id ->
                        RoomAccessibility.builder()
                                .room(room)
                                .accessibility(Accessibility.builder()
                                        .id(id)
                                        .build())
                                .build())
                .toList();
    }

    public List<RoomAmenity> getRoomAmenityList(Room room){
        return Arrays.stream(this.amenities).map(id ->
                        RoomAmenity.builder()
                                .room(room)
                                .amenity(Amenity.builder()
                                        .id(id)
                                        .build())
                                .build())
                .toList();
    }

    public List<RoomUniqueAmenity> getRoomUniqueAmenityList(Room room){
        return Arrays.stream(this.uniqueAmenities).map(id ->
                RoomUniqueAmenity.builder()
                        .room(room)
                        .uniqueAmenity(UniqueAmenity.builder()
                            .id(id)
                            .build())
                        .build())
                .toList();
    }

    public List<Integer> getDeleteRoomAmenityByIdList(Room room) {
        List<Integer> amenityIdList = new ArrayList<>(Arrays.asList(this.amenities));
        return room.getRoomAmenities().stream()
                .map(e -> e.getAmenity().getId())
                .filter(id -> !amenityIdList.contains(id))
                .toList();
    }
    public List<RoomAmenity> getAddRoomAmenityList(Room room) {
        List<Integer> amenityIdList =
                room.getRoomAmenities().stream().map(e -> e.getAmenity().getId()).toList();
        return Arrays.stream(this.amenities)
                .filter(e -> !amenityIdList.contains(e))
                .map(id -> RoomAmenity.builder()
                        .room(room)
                        .amenity(Amenity.builder()
                                .id(id)
                                .build())
                        .build())
                .toList();
    }

    public List<Integer> getDeleteRoomUniqueAmenityByIdList(Room room) {
        List<Integer> UniqueAmenityIdList = new ArrayList<>(Arrays.asList(this.uniqueAmenities));
        return room.getRoomUniqueAmenities().stream()
                .map(e -> e.getUniqueAmenity().getId())
                .filter(id -> !UniqueAmenityIdList.contains(id))
                .toList();
    }
    public List<RoomUniqueAmenity> getAddRoomUniqueAmenityList(Room room) {
        List<Integer> UniqueAmenityIdList =
                room.getRoomUniqueAmenities().stream().map(e -> e.getUniqueAmenity().getId()).toList();
        return Arrays.stream(this.uniqueAmenities)
                .filter(e -> !UniqueAmenityIdList.contains(e))
                .map(id -> RoomUniqueAmenity.builder()
                        .room(room)
                        .uniqueAmenity(UniqueAmenity.builder()
                                .id(id)
                                .build())
                        .build())
                .toList();
    }

    public List<Integer> getDeleteRoomAccessibilityByIdList(Room room) {
        List<Integer> accessibilityIdList = new ArrayList<>(Arrays.asList(this.accessibility));
        return room.getRoomAccessibility().stream()
                .map(e -> e.getAccessibility().getId())
                .filter(id -> !accessibilityIdList.contains(id))
                .toList();
    }
    public List<RoomAccessibility> getAddRoomAccessibilityList(Room room) {
        List<Integer> accessibilityIdList =
                room.getRoomAccessibility().stream().map(e -> e.getAccessibility().getId()).toList();
        return Arrays.stream(this.accessibility)
                .filter(e -> !accessibilityIdList.contains(e))
                .map(id -> RoomAccessibility.builder()
                        .room(room)
                        .accessibility(Accessibility.builder()
                                .id(id)
                                .build())
                        .build())
                .toList();
    }
}
