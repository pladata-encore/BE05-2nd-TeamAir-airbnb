package com.air.room;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RoomApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomApplication.class, args);
	}

}
