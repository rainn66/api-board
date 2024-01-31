package com.board.back.config.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.board.back.service.UserService;
import com.board.back.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class LoginRequestFilter extends OncePerRequestFilter {

    private static final String[] notFilteringList = {"/side", "/board", "/user/login", "/user/signup", "/css/*", "/*.ico"};

    private final JwtUtil jwtUtil;

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String requestURI = request.getRequestURI();
            log.info("======================== LoginRequestFilter start ========================");
            if (PatternMatchUtils.simpleMatch(notFilteringList, requestURI)) {
                doFilter(request, response, filterChain);
            } else {
                String authHeader = request.getHeader("Authorization");
                if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
                    String jwtToken = authHeader.substring(authHeader.lastIndexOf("Bearer ") + 1);
                    DecodedJWT decodedJWT = jwtUtil.decodeJwt(jwtToken);
                    String userId = decodedJWT.getClaim("userId").asString();
                    if (!userService.validateDuplicateUsers(userId)) {
                        //회원 있으면 false
                        doFilter(request, response, filterChain);
                    }
                }
            }
            log.info("======================== LoginRequestFilter end ========================");
        } catch (Exception e) {
            log.error("error :", e);
        }
    }
}
