package com.air.room.service;

import com.air.room.dto.response.RoomSimpleInfoResponse;
import com.air.room.utills.TokenInfo;
import com.air.room.dto.SearchRoomDto;
import com.air.room.dto.request.RoomRequest;
import com.air.room.dto.response.RoomAllInfoResponse;

import java.util.List;

public interface RoomService {
    List<RoomSimpleInfoResponse> getAllRoom();
    RoomAllInfoResponse getRoomById(Integer roomId);
    List<RoomSimpleInfoResponse> getAllRoomByUserId(Integer userId);
    List<RoomSimpleInfoResponse> searchRoom(SearchRoomDto searchRoomDto);
    void addRoom(Integer userId, String userName, RoomRequest req);
    void updateRoom(Integer roomId, TokenInfo tokenInfo, RoomRequest req);
    void deleteRoom(Integer roomId);
    void deleteRoomByUserId(Integer userId);
}
