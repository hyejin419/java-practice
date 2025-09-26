package com.koreait.restapi.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "MySuperSecretKeyForJWTTokenWhichIsVerySecure12345";
    private final long EXPIRATION = 1000 * 60 * 60;
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username, int userId) {
        return Jwts.builder().setSubject(username)
                .claim("id", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    //토큰에서 username 추출
    public String getUsernameFromToken(String token) {
        return parseClaims(token).getSubject();
    }

    //토큰에서 userId 추출
    public Integer getUserIdFromToken(String token) {
        return parseClaims(token).get("id", Integer.class);
    }

    //토큰 유효성 검사
    public boolean isTokenValid(String token) {
        try {
            parseClaims(token);
            return true;
        }catch(JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    //내부 claims 파싱 메서드
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder() // JWT를 파싱하기 위한 설정 시작!
                .setSigningKey(key)// JWT의 서명을 검증하기 위해 사용할 비밀키/공개키 지정
                .build() // 설정된 파서 객체 생성.
                .parseClaimsJws(token) //주어진 JWT 문자열을 검증하고 파싱.
                .getBody();  // 파싱된 JWT에서 claims 부분만 추출.
    }

    //요청 헤더에서 토큰 추출 → userId 반환
    public Integer getUserIdFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if(bearer != null && bearer.startsWith("Bearer ")) {
            String token = bearer.substring(7);
            return getUserIdFromToken(token);
        }
        throw new RuntimeException("토큰 에러!");
    }
    //요청 헤더에서 토큰 추출 → username 반환 (NEW)
    public String getUsernameFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            String token = bearer.substring(7);
            return getUsernameFromToken(token);
        }
        throw new RuntimeException("토큰 에러!");
    }

}
