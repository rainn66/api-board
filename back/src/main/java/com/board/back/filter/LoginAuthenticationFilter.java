//package com.board.back.filter;
//
//import com.board.back.config.impl.CustomUserDetails;
//import com.board.back.form.validation.UserLoginForm;
//import com.board.back.util.JwtUtil;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.io.IOException;
//
//@Slf4j
//@RequiredArgsConstructor
////Spring Security : @PostMapping("/login")
//public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final AuthenticationManager authenticationManager;
//
//    private final JwtUtil jwtUtil;
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try {
//            log.info("######## 로그인 인증 절차 실행 중");
//
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            UserLoginForm userLoginForm = objectMapper.readValue(request.getInputStream(), UserLoginForm.class);
//            log.info("######## input userId : {}", userLoginForm.getUserId());
//            UsernamePasswordAuthenticationToken authenticationToken =
//                    new UsernamePasswordAuthenticationToken(userLoginForm.getUserId(), userLoginForm.getPassword());
//            log.info("######## 로그인 인증 완료");
//
//            //UserService.loadUserByUsername 실행 : UserDetailsService 상속
//            Authentication authentication = authenticationManager.authenticate(authenticationToken);
//
//            //Security authentication setting
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            return authentication;
//        } catch (Exception e) {
//            log.error("error :", e);
//        }
//        return null;
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        log.info("######## jwt 생성 중");
//        CustomUserDetails userDetails = (CustomUserDetails) authResult.getPrincipal();
//
//        String jwt = jwtUtil.createJwt(userDetails.getUsers().getUserId(), userDetails.getUsers().getUserNm());
//
//        log.info("######## jwt token : {}", jwt);
//        response.addHeader("Authorization", "Bearer " + jwt);
//    }
//}
