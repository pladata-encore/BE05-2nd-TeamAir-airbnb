package com.air.user.api;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiRoom {
    private final FeignRoom feignRoom;

    @Async
    public void disableRoom(Integer userId){
        feignRoom.disableRoom(userId);
    }
}
