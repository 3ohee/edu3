package com.ict.edu3.common.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret; // 비밀 키
    @Value("${jwt.expiration}")
    private long expiration; // 만료

    private SecretKey getKey() {
        byte[] keyByte = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyByte);
    }

    // 토큰 생성
    public String generateToken(String id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("phone", "010-7777-8888");

        return generateToken(id, claims);
    }

    // 토근 생성
    public String generateToken(String username, Map<String, Object> claims) {
        // 내용을 더 추가하고 싶으면
        // 보안 떄문에 중요한 정보를 넣으면 안됨 ~
        claims.put("email", "jsoy0707@naver.com");
        claims.put("phone", "010-0319-0707");

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    
    // 모든 정보 추출
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
        .setSigningKey(getKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> clauimResolver) {
        final Claims claims = extractAllClaims(token);
        return clauimResolver.apply(claims);
    }

    // 토큰을 받아서 이름을 추출한다.
    public String extractuserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 토큰 검사
    // UserDetail은 유저 정보를 로드하며, 관리하는 역할을 한다.
    public Boolean validateToken(String token, UserDetails userDetails) {
        // jwt 토큰에서 subject 정보를 가져오는 것
        final String username = extractuserName(token);
        return (username.equals(userDetails.getUsername()) && isTokenExpried(token));
    }

    // 만료시간 점검
    private Boolean isTokenExpried(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}