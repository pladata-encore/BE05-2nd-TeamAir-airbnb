package com.air.room.service;

import com.air.room.TestInit;
import com.air.room.utills.TokenInfo;
import com.air.room.dto.request.RoomLocationRequest;
import com.air.room.dto.request.RoomRequest;
import com.air.room.dto.request.SafetySupplyRequest;
import com.air.room.dto.response.RoomAllInfoResponse;
import com.air.room.exception.DisabledArgumentException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
@Transactional
class RoomServiceImplTest extends TestInit {

    @Nested
    class Tests {
        @Test
        void findByRoomId() {
            System.out.println("--stert test 'findByRoomId'--");

            // give
            Integer roomId = testRoomIdList.get(0);

            // when
            RoomAllInfoResponse RoomById = roomService.getRoomById(roomId);

            // then
            Assertions.assertNotNull(RoomById);
            Assertions.assertEquals(testRoom1.getName(), RoomById.name());

            System.out.println("--end test 'findByRoomId'--");
        }

        @Test
        void deleteByRoomId() {
            System.out.println("--stert test 'deleteByRoomId'--");

            // give
            Integer roomId = testRoomIdList.get(0);

            // when
            roomService.deleteRoom(roomId);

            // then
            Assertions.assertThrows(DisabledArgumentException.class, () -> {
                roomService.getRoomById(roomId);
            });

            System.out.println("--end test 'deleteByRoomId'--");
        }

        @Test
        void updateByRoomId() {
            System.out.println("--stert test 'updateByRoomId'--");

            // give
            TokenInfo testTokenInfo = new TokenInfo(1, "qwer");
            RoomRequest roomRequest = new RoomRequest(
                    1,
                    "변경된 이름",
                    "변경된 설명",
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    2,
                    1000*60*60*(10-9),
                    1000*60*60*(15-9),
                    "변경된 규칙",
                    "2024-04-02",
                    "2026-01-02",
                    new Integer[]{1},
                    new Integer[]{1},
                    new Integer[]{1},
                    new RoomLocationRequest(
                            new BigDecimal("35.2222"),
                            new BigDecimal("127.2222")
                    ),
                    new SafetySupplyRequest(
                            true,
                            true,
                            true,
                            true
                    )
            );

            // when
            roomService.updateRoom(testRoomIdList.get(1), testTokenInfo, roomRequest);

            // then
            Assertions.assertNotEquals(testRoom2.getName(), roomRequest.name());
            Assertions.assertNotEquals(
                    testRoom2.getRoomAccessibility() == null ? 0 : testRoom2.getRoomAccessibility().size(),
                    roomRequest.accessibility().length);

            System.out.println("--end test 'updateByRoomId'--");
        }
    }

}