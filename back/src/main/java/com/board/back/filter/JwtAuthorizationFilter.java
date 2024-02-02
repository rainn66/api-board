package com.board.back.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.board.back.config.impl.CustomUserDetails;
import com.board.back.entity.User;
import com.board.back.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private static final String[] notFilteringList = {"/side", "/board", "/user/login", "/css/*", "/*.ico", "/user/signup"};

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String myJwt = request.getHeader("Authorization");
        String requestURI = request.getRequestURI();

        if (myJwt == null) {
            chain.doFilter(request, response);
            return;
        }

        String jwt = myJwt.replace("Bearer ", "");
        try {
            //Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //JWTVerifier verifier = JWT.require(algorithm).build();
            //return verifier.verify(jwt);

            DecodedJWT decodedJWT = JwtUtil.decodeJwt(jwt);
            if (decodedJWT != null && !PatternMatchUtils.simpleMatch(notFilteringList, requestURI)) {
                String userId = decodedJWT.getClaim("userId").asString();
                String userNm = decodedJWT.getClaim("userNm").asString();
                User user = User.builder().userId(userId).userNm(userNm).build();
                CustomUserDetails customUserDetails = new CustomUserDetails(user);
                log.info("CustomUserDetails : {}", userId);
                Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, customUserDetails.getPassword(), null);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.error("", e);
        } finally {
            chain.doFilter(request, response);
        }
    }


    //private void setErrorResponse(HttpServletResponse response, Exception e) {
    //    ObjectMapper objectMapper = new ObjectMapper();
    //    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    //
    //    try{
    //        response.getWriter().write(objectMapper.writeValueAsString());
    //    }catch (IOException e){
    //        log.error("", e);
    //    }
    //}

}
