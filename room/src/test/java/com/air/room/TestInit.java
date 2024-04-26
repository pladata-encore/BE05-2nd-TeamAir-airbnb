package com.air.room;

import com.air.room.utills.JwtTokenUtils;
import com.air.room.dto.request.RoomLocationRequest;
import com.air.room.dto.request.RoomRequest;
import com.air.room.dto.request.SafetySupplyRequest;
import com.air.room.global.domain.entity.Room;
import com.air.room.global.domain.entity.RoomLocation;
import com.air.room.global.domain.entity.SafetySupply;
import com.air.room.global.domain.repository.*;
import com.air.room.service.RoomService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class TestInit {
    @Autowired
    protected RoomService roomService;
    @Autowired
    protected EntityManager em;
    @Autowired
    protected RoomRepository roomRepository;
    @Autowired
    protected RoomAccessibilityRepository roomAccessibilityRepository;
    @Autowired
    protected RoomAmenityRepository roomAmenityRepository;
    @Autowired
    protected RoomUniqueAmenityRepository roomUniqueAmenityRepository;
    @Autowired
    protected RoomLocationRepository roomLocationRepository;
    @Autowired
    protected SafetySupplyRepository safetySupplyRepository;
    @Autowired
    protected JwtTokenUtils jwtTokenUtils;
    protected Room testRoom1;
    protected Room testRoom2;

    protected List<Integer> testRoomIdList;

    @BeforeEach
    public void initData() {
        testRoomIdList = new ArrayList<>();
        roomRepository.deleteAll();

        RoomRequest roomReq1 = new RoomRequest(
                1,
                "짱멋진 숙소",
                "이런숙소 어디에서도 볼수업읍니다 지금당장 예약하세요",
                1,
                4,
                1,
                1,
                2,
                1,
                50000,
                5000,
                1000*60*60*(6-9+24),
                1000*60*60*(23-9),
                "간지나는 사람만 이용가능",
                "2024-07-01",
                "2024-07-07",
                new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13},
                new Integer[]{1,2,3,4,5,6,7,8},
                new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13},
                new RoomLocationRequest(
                        new BigDecimal("35.123456789"),
                        new BigDecimal("127.12345678")
                ),
                new SafetySupplyRequest(
                        true,
                        true,
                        true,
                        true
                )
        );
        RoomRequest roomReq2 = new RoomRequest(
                1,
                "겁나 싼 숙소",
                "싼게 비지떡이라는 말이 무엇인지 알려주도록 하지",
                1,
                1,
                1,
                0,
                0,
                0,
                1000,
                0,
                1000*60*60*(9-9),
                1000*60*60*(21-9),
                "마음대로 쓰세요",
                "2024-04-01",
                "2025-12-31",
                new Integer[]{},
                new Integer[]{},
                new Integer[]{},
                new RoomLocationRequest(
                        new BigDecimal("35.987654321"),
                        new BigDecimal("127.98765432")
                ),
                new SafetySupplyRequest(
                        false,
                        false,
                        false,
                        false
                )
        );
        Integer userId = 1;
        String userName = "user name";
        addRoom(userId, userName, roomReq1);
        addRoom(userId, userName, roomReq2);
        testRoom1 = roomReq1.toEntity(userId, userName);
        testRoom2 = roomReq2.toEntity(userId, userName);
        System.out.println("--test room1,2 add--");

        em.flush();
        em.clear();

        System.out.println("--entity manager flush and clear--");
    }


    private void addRoom(Integer userId, String userName, RoomRequest req) {
        Room room = req.toEntity(userId, userName);
        roomRepository.save(room);
        testRoomIdList.add(room.getId());


        RoomLocation roomLocation = req.roomLocationRequest().toEntity(room);
        roomLocationRepository.save(roomLocation);

        SafetySupply safetySupply = req.safetySupplyRequest().toEntity(room);
        safetySupplyRepository.save(safetySupply);

        roomAccessibilityRepository.saveAll(req.getRoomAccessibilityList(room));
        roomAmenityRepository.saveAll(req.getRoomAmenityList(room));
        roomUniqueAmenityRepository.saveAll(req.getRoomUniqueAmenityList(room));
    }
}
