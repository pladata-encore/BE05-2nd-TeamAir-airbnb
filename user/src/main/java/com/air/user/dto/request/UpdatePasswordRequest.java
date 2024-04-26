package com.air.user.dto.request;

public record UpdatePasswordRequest(
        String oldPassword, String newPassword
){
}
