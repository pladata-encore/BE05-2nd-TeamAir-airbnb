package com.air.room.controller;

import com.air.room.service.RoomService;
import com.air.room.utills.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/room/test")
public class TestController {
    private final RoomService roomService;
    private final JwtTokenUtils jwtTokenUtils;

    @GetMapping("/token")
    public String testToken() {
        return jwtTokenUtils.createToken(1,"qwer");
    }

//    @GetMapping()
//    public List<RoomInfoAllResponse> test() {
//        return roomService.testQuery();
//    }

}
