package com.board.back.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
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
                .withExpiresAt(new Date(System.currentTimeMillis() + 6000 * 10)) //1분
                .withClaim("userId", userId)
                .withClaim("userNm", userNm)
                .withIssuedAt(new Date())
                .sign(algorithm);
        log.info("################## {}", jwt);
        return jwt;
    }


    public static DecodedJWT decodeJwt(String jwt) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(jwt);
        } catch (JWTVerificationException e) {
            log.error("JWTVerificationException: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("IllegalArgumentException: {}", e.getMessage());
        }

        return null;
    }


    public static String tokenToUserId(String fullJwt) {
        String jwt = fullJwt.substring(fullJwt.lastIndexOf("Bearer ") + 1);

        DecodedJWT tokenInfo = decodeJwt(jwt);
        String userId = "";
        if (tokenInfo != null) {
            //userId = JWT.require(Algorithm.HMAC512(secret)).build().verify(jwt).getClaim("userId").asString();
            userId = tokenInfo.getClaim("userId").asString();
        }
        return userId;
    }


}
