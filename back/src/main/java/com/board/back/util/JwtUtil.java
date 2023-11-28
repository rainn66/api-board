package com.board.back.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.board.back.domain.UserEntity;
import com.board.back.repository.UserRepository;
import com.board.back.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    String secret;

    private UserService userService;

    private UserRepository userRepository;

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

    public String validateToken(String jwt) {

        final String userId = getUserFromToken(jwt);

        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        return userEntity.getUserId();
    }

    public String getUserFromToken(String jwt) {

        DecodedJWT tokenInfo = decodeJwt(jwt);
        String userId = "";

        if (tokenInfo != null) {
            userId = tokenInfo.getClaim("userNm").asString();
        }

        return userId;
    }


}
