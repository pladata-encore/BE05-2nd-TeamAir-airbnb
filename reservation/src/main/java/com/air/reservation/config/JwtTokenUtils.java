package com.air.reservation.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenUtils {
    @Value(("${token.expiration}"))
    private Long tokenExpiration;
    @Value("${token.secret}")
    private String tokenSecret;

    // 토큰 parse
    public TokenInfo parseToken(String token){
        Claims payload = (Claims) Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(tokenSecret.getBytes()))
                .build()
                .parse(token)
                .getPayload();
        return TokenInfo.fromClaims(payload);
    }

    public String createToken(Integer id, String name) {
        SecretKey secretKey = Keys.hmacShaKeyFor(tokenSecret.getBytes());
        return Jwts.builder()
                .claim("id", id)
                .claim("name", name)
                .signWith(secretKey)
                .expiration(new Date(new Date().getTime() + tokenExpiration))
                .compact();
    }
}
