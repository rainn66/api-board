package com.board.back.service.impl;

import com.board.back.config.impl.CustomUserDetails;
import com.board.back.entity.User;
import com.board.back.exception.Exception400;
import com.board.back.exception.Exception401;
import com.board.back.form.validation.UserLoginForm;
import com.board.back.form.validation.UserSaveForm;
import com.board.back.repository.UserRepository;
import com.board.back.service.UserService;
import com.board.back.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    /**
     * 회원 확인
     */
    @Transactional
    public String login(UserLoginForm userForm) {

        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(userForm.getUserId(), userForm.getPassword());
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

            //마지막 로그인 날짜 업데이트
            customUserDetails.getUser().updateLastLoginDt(LocalDateTime.now());
            log.info("authentication {}", authentication);
            return JwtUtil.createJwt(customUserDetails.getUser().getUserId(), customUserDetails.getUser().getUserNm());
        } catch (Exception e) {
            log.error("", e);
            throw new Exception401("로그인 인증 오류(회원 정보가 올바르지 않습니다.)");
        }
    }

    /**
     * 회원 가입
     */
    @Transactional
    public String signUp(UserSaveForm userForm) throws Exception {
        Optional<User> findUser = userRepository.findByUserId(userForm.getUserId());
        if (findUser.isPresent()) {
            throw new Exception400("이미 존재하는 회원입니다.");
        }

        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            User users = User.builder()
                    .userId(userForm.getUserId())
                    .userNm(userForm.getUserNm())
                    .password(passwordEncoder.encode(userForm.getPassword()))
                    .build();
            return userRepository.save(users).getUserNm();
        } catch (Exception e) {
            log.error("", e);
            throw new Exception(e.getMessage());
        }
    }
}
