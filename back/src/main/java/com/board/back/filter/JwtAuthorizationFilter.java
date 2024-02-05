package com.board.back.filter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.board.back.config.impl.CustomUserDetails;
import com.board.back.entity.User;
import com.board.back.exception.Exception401;
import com.board.back.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private static final String[] notFilteringList = {"/", "/side", "/board", "/user/login", "/css/*", "/*.ico", "/user/signup"};

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String myJwt = request.getHeader("Authorization");
        String requestURI = request.getRequestURI();

        if (myJwt == null) {
            if (!PatternMatchUtils.simpleMatch(notFilteringList, requestURI)) {
                setErrorResponse(response, new Exception401("NOT_LOGIN", "로그인이 필요합니다."));
            } else {
                chain.doFilter(request, response);
            }
            return;
        }

        String jwt = myJwt.replace("Bearer ", "");
        try {
            DecodedJWT decodedJWT = JwtUtil.decodeJwt(jwt);
            if (decodedJWT != null) {
                String userId = decodedJWT.getClaim("userId").asString();
                String userNm = decodedJWT.getClaim("userNm").asString();
                User user = User.builder().userId(userId).userNm(userNm).build();
                CustomUserDetails customUserDetails = new CustomUserDetails(user);
                Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, customUserDetails.getPassword(), null);

                log.info("SecurityContextHolder.setAuthentication : {}", userId);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
            }
        } catch (TokenExpiredException e) {
            log.error("", e);
            //errorTitle = 'EXPIRED_ONLY' 이면 로그아웃만 진행 'EXPIRED' 이면 로그인화면으로 이동
            String errorTitle = PatternMatchUtils.simpleMatch(notFilteringList, requestURI) ? "EXPIRED_ONLY" : "EXPIRED";
            setErrorResponse(response, new Exception401(errorTitle, "로그인 정보가 만료되었습니다. 로그아웃 됩니다."));
        } catch (Exception e) {
            setErrorResponse(response, new Exception401());
            log.error("", e);
        }
    }

    private void setErrorResponse(HttpServletResponse response, Exception401 e) {
        response.setStatus(401);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        try{
            String json = new ObjectMapper().writeValueAsString(e.body());
            response.getWriter().write(json);
        } catch (IOException ie){
            log.error("", ie);
        }
    }

}
