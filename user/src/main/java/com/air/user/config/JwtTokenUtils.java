package com.air.user.config;

import com.air.user.global.domain.entity.User;
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

    // 토큰 생성
    public String createToken(User user) {
        SecretKey secretKey = Keys.hmacShaKeyFor(tokenSecret.getBytes());
        String token = Jwts.builder()
                .claim("id", user.getId())
                .claim("username", user.getUserName())
                .signWith(secretKey)
                .expiration(new Date(new Date().getTime() + tokenExpiration))
                .compact();
        return token;
    }
    // 토큰 검증
    public boolean validateToken(String token){
        Claims payload = (Claims) Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(tokenSecret.getBytes()))
                .build()
                .parse(token)
                .getPayload();
        System.out.println(payload);
        return true;
    }
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
}
