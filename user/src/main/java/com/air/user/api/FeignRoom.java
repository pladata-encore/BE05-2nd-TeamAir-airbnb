package com.air.user.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("ROOM-SERVICE")
public interface FeignRoom {
    @DeleteMapping("api/v1/room/host/{id}")
    void disableRoom(@PathVariable Integer id);
}
