package com.air.user.dto.request;

import com.air.user.global.domain.entity.User;

public record SignUpRequest(
        String email, String password, String userName
) {
    public User toEntity(String encodedPW){
        return User.builder()
                .email(email)
                .password(encodedPW)
                .userName(userName)
                .build();
    }
}
