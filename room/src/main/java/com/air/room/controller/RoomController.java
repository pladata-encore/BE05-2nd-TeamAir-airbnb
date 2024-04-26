package com.air.room.controller;

import com.air.room.dto.response.RoomSimpleInfoResponse;
import com.air.room.utills.JwtTokenUtils;
import com.air.room.utills.TokenInfo;
import com.air.room.dto.SearchRoomDto;
import com.air.room.dto.request.RoomRequest;
import com.air.room.dto.response.RoomAllInfoResponse;
import com.air.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/room")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoomController {
    private final RoomService roomService;
    private final JwtTokenUtils jwtTokenUtils;

    @GetMapping
    public List<RoomSimpleInfoResponse> getAllRooms() {
        return roomService.getAllRoom();
    }

    @GetMapping("/{id}")
    public RoomAllInfoResponse getRoomById(@PathVariable Integer id) {
        return roomService.getRoomById(id);
    }

    @GetMapping("/host/{id}")
    public List<RoomSimpleInfoResponse> getRoomByHostId(@PathVariable Integer id) {
        return roomService.getAllRoomByUserId(id);
    }

    @GetMapping("/search")
    public List<RoomSimpleInfoResponse> getRoomBySearch(
            @RequestParam Optional<Integer> cityCode,
            @RequestParam Optional<Integer> roomType,
            @RequestParam Optional<Integer> personNum,
            @RequestParam Optional<Integer> roomReserve,
            @RequestParam Optional<Integer> bedroomNum,
            @RequestParam Optional<Integer> bedNum,
            @RequestParam Optional<Integer> bathroomNum,
            @RequestParam Optional<Integer> minPrice,
            @RequestParam Optional<Integer> maxPrice,
            @RequestParam Optional<LocalDate> reserveStart,
            @RequestParam Optional<LocalDate> reserveEnd,
            @RequestParam Optional<Integer[]> amenities,
            @RequestParam Optional<Integer[]> uniqueAmenities,
            @RequestParam Optional<Integer[]> accessibilites,
            @RequestParam Optional<Boolean> fireAlarm,
            @RequestParam Optional<Boolean> aidKit,
            @RequestParam Optional<Boolean> extinguisher,
            @RequestParam Optional<Boolean> coAlarm
    ) {
        return roomService.searchRoom(SearchRoomDto.builder()
                .cityCode(cityCode.orElse(null))
                .roomType(roomType.orElse(null))
                .personNum(personNum.orElse(null))
                .roomReserve(roomReserve.orElse(null))
                .bedroomNum(bedroomNum.orElse(null))
                .bedNum(bedNum.orElse(null))
                .bathroomNum(bathroomNum.orElse(null))
                .minPrice(minPrice.orElse(null))
                .maxPrice(maxPrice.orElse(null))
                .reserveStart(reserveStart.orElse(null))
                .reserveEnd(reserveEnd.orElse(null))
                .amenities(amenities.orElse(null))
                .uniqueAmenities(uniqueAmenities.orElse(null))
                .accessibilites(accessibilites.orElse(null))
                .fireAlarm(fireAlarm.orElse(null))
                .aidKit(aidKit.orElse(null))
                .extinguisher(extinguisher.orElse(null))
                .coAlarm(coAlarm.orElse(null))
                .build()
        );
    }

    @PostMapping
    public void createRoom(
            @RequestBody RoomRequest req,
            @RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtTokenUtils.parseToken(token);
        roomService.addRoom(tokenInfo.id(), tokenInfo.name(), req);
    }

    @PutMapping("/{id}")
    public void updateRoom(
            @PathVariable Integer id,
            @RequestBody RoomRequest req,
            @RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtTokenUtils.parseToken(token);
        roomService.updateRoom(id, tokenInfo, req);
    }

    @DeleteMapping("/{id}")
    public void deleteRoomById(@PathVariable Integer id) {
        roomService.deleteRoom(id);
    }

    @DeleteMapping("/host/{id}")
    public void deleteRoomByUserId(@PathVariable Integer id) {
        roomService.deleteRoomByUserId(id);
    }

}
