package com.board.back.util;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.board.back.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenRequestFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if ("/user/login".equals(request.getRequestURI()) || "/bbsMainList".equals(request.getRequestURI())) {
                doFilter(request, response, filterChain);
            } else {
                String token = parseJwt(request);
                if (token == null) {
                    response.sendError(403);    //accessDenied
                } else {
                    DecodedJWT tokenInfo = jwtUtil.decodeJwt(token);
                    if (tokenInfo != null) {
                        String userId = tokenInfo.getClaim("userId").asString();
                        UserDetails loginUser = userService.loadUserByUsername(userId);
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            loginUser, null, loginUser.getAuthorities()
                        );

                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        doFilter(request, response, filterChain);

                    } else {
                        log.error("### TokenInfo is Null");
                    }
                }
            }
        } catch (Exception e) {
            log.error("### Filter Exception {}", e.getMessage());
        }
    }

    public String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }
}