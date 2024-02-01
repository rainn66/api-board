package com.board.back.service.impl;

import com.board.back.config.impl.CustomUserDetails;
import com.board.back.entity.User;
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
    public String login(UserLoginForm userForm) throws Exception {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(userForm.getUserId(), userForm.getPassword());
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            return JwtUtil.createJwt(customUserDetails.getUser().getUserId(), customUserDetails.getUser().getUserNm());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 회원 가입
     */
    @Transactional
    public String signUp(UserSaveForm userForm) throws Exception {
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            User users = User.builder()
                    .userId(userForm.getUserId())
                    .userNm(userForm.getUserNm())
                    .password(passwordEncoder.encode(userForm.getPassword()))
                    .build();
            return userRepository.save(users).getUserNm();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 회원 중복 체크
     */
    public boolean validateDuplicateUsers(String userId) {
        return userRepository.findByUserId(userId).isEmpty(); //true=회원가입가능
    }
}
