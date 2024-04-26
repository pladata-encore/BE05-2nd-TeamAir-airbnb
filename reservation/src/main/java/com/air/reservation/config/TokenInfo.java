package com.air.reservation.config;

import io.jsonwebtoken.Claims;

public record TokenInfo(
        Integer id, String userName
) {
    public static TokenInfo fromClaims(Claims claims){
        Integer id = claims.get("id", Integer.class);
        String userName = claims.get("username", String.class);
        return new TokenInfo(id, userName);
    }
}
