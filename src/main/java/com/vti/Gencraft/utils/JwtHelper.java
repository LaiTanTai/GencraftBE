package com.vti.Gencraft.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
@Service
public class JwtHelper {
    @Value("${jwt.secret.key}")
    private String secret;
    public String generateToken(String data) {

        Key key = Keys.hmacShaKeyFor(Encoders.BASE64.encode(secret.getBytes()).getBytes());
        String token = Jwts.builder()
                .setSubject(data)
                .signWith(key)
                .compact();
        return token;
    }

    public Claims decodeToken(String token){
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        Claims claims =  Jwts.parserBuilder()
                .setSigningKey(key)
                .build().parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
