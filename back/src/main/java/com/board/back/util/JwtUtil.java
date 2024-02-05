package com.board.back.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private static final String SECRET = "jwt-secret!@#";

    public static String createJwt(String userId, String userNm){
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        String jwt = "Bearer " + JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * 60 * 10))) //10분
                //.withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 10)) //10초
                .withClaim("userId", userId)
                .withClaim("userNm", userNm)
                .withIssuedAt(new Date())
                .sign(algorithm);
        log.info("JWT : {}", jwt);
        return jwt;
    }


    public static DecodedJWT decodeJwt(String jwt) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(jwt);
    }


    public static String tokenToUserId(String fullJwt) {
        log.info("fullJwt : {}", fullJwt);
        String jwt = fullJwt.substring(7);
        log.info("jwt : {}", jwt);
        DecodedJWT tokenInfo = decodeJwt(jwt);
        String userId = "";
        if (tokenInfo != null) {
            //userId = JWT.require(Algorithm.HMAC512(secret)).build().verify(jwt).getClaim("userId").asString();
            userId = tokenInfo.getClaim("userId").asString();
        }
        return userId;
    }


}
