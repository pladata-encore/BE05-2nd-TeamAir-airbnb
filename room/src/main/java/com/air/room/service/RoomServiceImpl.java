package com.air.room.service;

import com.air.room.dto.response.RoomSimpleInfoResponse;
import com.air.room.utills.FilterUtils;
import com.air.room.utills.TokenInfo;
import com.air.room.dto.SearchRoomDto;
import com.air.room.dto.request.RoomRequest;
import com.air.room.dto.response.RoomAllInfoResponse;
import com.air.room.exception.DisabledArgumentException;
import com.air.room.exception.NotFoundException;
import com.air.room.global.domain.entity.*;
import com.air.room.global.domain.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomLocationRepository roomLocationRepository;
    private final SafetySupplyRepository safetySupplyRepository;
    private final RoomAccessibilityRepository roomAccessibilityRepository;
    private final RoomAmenityRepository roomAmenityRepository;
    private final RoomUniqueAmenityRepository roomUniqueAmenityRepository;

    @Override
    public List<RoomSimpleInfoResponse> getAllRoom() {
        return roomRepository.findAllRoomOnJPQL().stream()
                .filter(room -> !room.getIsDisable())
                .map(RoomSimpleInfoResponse::from)
                .toList();
    }

    @Override
    public RoomAllInfoResponse getRoomById(Integer id) {
//        Room room = roomRepository.findRoomByRoomIdOnJPQL(id).orElseThrow(() -> new NotFoundException("ROOM"));
        Room room = roomRepository.findRoomByRoomIdOnJPQL(id);
        if (room == null) throw new NotFoundException("ROOM");
        if (room.getIsDisable()) throw new DisabledArgumentException("ROOM");
        return RoomAllInfoResponse.from(room);
    }

    @Override
    public List<RoomSimpleInfoResponse> getAllRoomByUserId(Integer id) {
        return roomRepository.findAllRoomByUserIdOnJPQL(id).stream()
                .filter(room -> !room.getIsDisable())
                .map(RoomSimpleInfoResponse::from)
                .toList();
    }

    @Override
    public List<RoomSimpleInfoResponse> searchRoom(SearchRoomDto opt) {
        Stream<Room> allRoom = roomRepository.findAllRoomOnJPQL().stream();
        return allRoom
                .filter(room -> !room.getIsDisable())
                .filter(FilterUtils.isCityCodeEqualTo(opt.cityCode()))
                .filter(FilterUtils.isRoomTypeEqualTo(opt.roomType()))
                .filter(FilterUtils.isPersonNumMoreOrEqualTo(opt.personNum()))
                .filter(FilterUtils.isReserveOptionEqualTo(opt.roomReserve()))
                .filter(FilterUtils.isBedroomNumMoreOrEqualTo(opt.bedroomNum()))
                .filter(FilterUtils.isBedNumMoreOrEqualTo(opt.bedNum()))
                .filter(FilterUtils.isBathroomNumMoreOrEqualTo(opt.bathroomNum()))
                .filter(FilterUtils.isPriceMoreOrEqualTo(opt.minPrice()))
                .filter(FilterUtils.isPriceLessOrEqualTo(opt.maxPrice()))
                .filter(FilterUtils.isRoomAmenitiesContainTo(opt.amenities()))
                .filter(FilterUtils.isRoomUniqueAmenitiesContainTo(opt.uniqueAmenities()))
                .filter(FilterUtils.isRoomAccessibilitiesContainTo(opt.accessibilites()))
                .filter(FilterUtils.isSafetySupplyEqualTo(SafetySupply.builder()
                        .fireAlarm(opt.fireAlarm())
                        .aidKit(opt.aidKit())
                        .extinguisher(opt.extinguisher())
                        .coAlarm((opt.coAlarm()))
                        .build()))
                .map(RoomSimpleInfoResponse::from)
                .toList();
    }

    @Override
    @Transactional
    public void addRoom(Integer userId, String userName, RoomRequest req) {
        Room room = req.toEntity(userId, userName);
        roomRepository.save(room);

        RoomLocation roomLocation = req.roomLocationRequest().toEntity(room);
        roomLocationRepository.save(roomLocation);

        SafetySupply safetySupply = req.safetySupplyRequest().toEntity(room);
        safetySupplyRepository.save(safetySupply);

        roomAccessibilityRepository.saveAll(req.getRoomAccessibilityList(room));
        roomAmenityRepository.saveAll(req.getRoomAmenityList(room));
        roomUniqueAmenityRepository.saveAll(req.getRoomUniqueAmenityList(room));
    }

    @Override
    @Transactional
    public void updateRoom(Integer roomId, TokenInfo tokenInfo, RoomRequest req) {
//        Room room = roomRepository.findRoomByRoomIdOnJPQL(roomId).orElseThrow(() -> new NotFoundException("ROOM"));
        Room room = roomRepository.findRoomByRoomIdOnJPQL(roomId);
        if (room == null) throw new NotFoundException("ROOM");
        if (room.getIsDisable()) throw new DisabledArgumentException("ROOM");
        Room roomReq = req.toEntity(roomId, tokenInfo.id(), tokenInfo.name());
        room.update(roomReq);

        RoomLocation roomLocation = roomLocationRepository.findByRoomId(roomId);
        roomLocation.update(req.roomLocationRequest().toEntity(roomReq));

        SafetySupply safetySupply = safetySupplyRepository.findByRoomId(roomId);
        safetySupply.update(req.safetySupplyRequest().toEntity(roomReq));

        roomAmenityRepository.deleteAllById(req.getDeleteRoomAmenityByIdList(room));
        roomAmenityRepository.saveAll(req.getAddRoomAmenityList(room));

        roomUniqueAmenityRepository.deleteAllById(req.getDeleteRoomUniqueAmenityByIdList(room));
        roomUniqueAmenityRepository.saveAll(req.getAddRoomUniqueAmenityList(room));

        roomAccessibilityRepository.deleteAllById(req.getDeleteRoomAccessibilityByIdList(room));
        roomAccessibilityRepository.saveAll(req.getAddRoomAccessibilityList(room));

    }

    @Override
    @Transactional
    public void deleteRoom(Integer roomId) {
//        roomRepository.deleteById(roomId);
//        Room room = roomRepository.findById(roomId).orElseThrow(() -> new NotFoundException("ROOM"));
//        room.disable();
        Room room = roomRepository.findRoomByRoomIdOnJPQL(roomId);
        if (room == null) throw new NotFoundException("ROOM");
        room.disable();
    }

    @Override
    @Transactional
    public void deleteRoomByUserId(Integer userId) {
        List<Room> rooms = roomRepository.findAllRoomByUserIdOnJPQL(userId);
        if (rooms == null) throw new NotFoundException("ROOM BY USER ID");
        rooms.forEach(Room::disable);
    }

}
