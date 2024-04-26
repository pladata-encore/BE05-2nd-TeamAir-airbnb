package com.air.room.utills;


import com.air.room.global.domain.entity.*;

import java.util.Arrays;
import java.util.function.Predicate;

public class FilterUtils {

    public static Predicate<Room> isCityCodeEqualTo(Integer cityCode) {
        return p -> cityCode == null || p.getCity().getCode().equals(cityCode);
    }
    public static Predicate<Room> isRoomTypeEqualTo(Integer roomType) {
        return p -> roomType == null || p.getType().equals(roomType);
    }
    public static Predicate<Room> isPersonNumMoreOrEqualTo(Integer personNum) {
        return p -> personNum == null || p.getMaxPeople() >= personNum;
    }
    public static Predicate<Room> isReserveOptionEqualTo(Integer reserveOption) {
        return p -> reserveOption == null || p.getReserveOption().equals(reserveOption);
    }
    public static Predicate<Room> isBedroomNumMoreOrEqualTo(Integer bedroomNum) {
        return p -> bedroomNum == null || p.getBedroomNum() >= bedroomNum;
    }
    public static Predicate<Room> isBedNumMoreOrEqualTo(Integer bedNum) {
        return p -> bedNum == null || p.getBedNum() >= bedNum;
    }
    public static Predicate<Room> isBathroomNumMoreOrEqualTo(Integer bathroomNum) {
        return p -> bathroomNum == null || p.getBathroomNum() >= bathroomNum;
    }
    public static Predicate<Room> isPriceMoreOrEqualTo(Integer price) {
        return p -> price == null || p.getPrice() >= price;
    }
    public static Predicate<Room> isPriceLessOrEqualTo(Integer price) {
        return p -> price == null || p.getPrice() <= price;
    }
    public static Predicate<Room> isRoomAmenitiesContainTo(Integer[] amenity) {
        return p -> amenity == null || p.getRoomAmenities().stream()
                .map(RoomAmenity::getId)
                .filter(e -> Arrays.stream(amenity).toList().contains(e))
                .toList().size() == amenity.length;
    }
    public static Predicate<Room> isRoomUniqueAmenitiesContainTo(Integer[] uniqueAmenity) {
        return p -> uniqueAmenity == null || p.getRoomUniqueAmenities().stream()
                .map(RoomUniqueAmenity::getId)
                .filter(e -> Arrays.stream(uniqueAmenity).toList().contains(e))
                .toList().size() == uniqueAmenity.length;
    }
    public static Predicate<Room> isRoomAccessibilitiesContainTo(Integer[] accessibility) {
        return p -> accessibility == null || p.getRoomAccessibility().stream()
                .map(RoomAccessibility::getId)
                .filter(e -> Arrays.stream(accessibility).toList().contains(e))
                .toList().size() == accessibility.length;
    }
    public static Predicate<Room> isSafetySupplyEqualTo(SafetySupply safetySupply) {
        return p ->
                (safetySupply.getFireAlarm() == null || p.getSafetySupply().getFireAlarm()) &&
                (safetySupply.getAidKit() == null || p.getSafetySupply().getAidKit()) &&
                (safetySupply.getExtinguisher() == null || p.getSafetySupply().getExtinguisher()) &&
                (safetySupply.getCoAlarm() == null || p.getSafetySupply().getCoAlarm());
    }
}
