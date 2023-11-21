package com.board.back.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    String secret;

    public String createJwt(String userId, String userNm){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
            .withIssuer("bbs")
            .withClaim("userId", userId)
            .withClaim("userNm", userNm)
            .withIssuedAt(new Date())
            .sign(algorithm);
    }


    public DecodedJWT decodeJwt(String jwt) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("bbs").build();
            return verifier.verify(jwt);
        } catch (JWTVerificationException e) {
            log.error("JWTVerificationException: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("IllegalArgumentException: {}", e.getMessage());
        }

        return null;
    }

}
