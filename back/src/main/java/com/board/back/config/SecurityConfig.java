package com.board.back.config;

import com.board.back.config.filter.LoginAuthenticationFilter;
import com.board.back.config.filter.LoginRequestFilter;
import com.board.back.service.UserService;
import com.board.back.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //세션 필요없음 -> 권한 사용x -> DB 권한 컬럼 필요없음
    //1. 홈, 목록, 회원가입, 로그인화면을 제외한 요청 시 로그인 여부 필터 구현
    //2. 수정/삭제 -> 글 작성자만 확인하면됨 -> 이걸 필터로 구현 가능?

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                .formLogin(AbstractHttpConfigurer::disable)
                .addFilter(new LoginAuthenticationFilter(authenticationManager, jwtUtil))
                .addFilter(new LoginRequestFilter(jwtUtil, userService));
                //.addFilterBefore(tokenRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}