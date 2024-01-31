package com.board.back.service.impl;

import com.board.back.config.impl.CustomUserDetails;
import com.board.back.entity.Users;
import com.board.back.form.validation.UserSaveForm;
import com.board.back.repository.UserRepository;
import com.board.back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * 회원 확인, UserDetail 세팅
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        List<GrantedAuthority> authorities = new ArrayList<>();

        Users usersEntity = userRepository.findByUserId(userId)
            .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        usersEntity.updateLastLoginDt(LocalDateTime.now());//마지막 접속일 갱신

        return new CustomUserDetails(usersEntity);
    }

    /**
     * 회원 가입
     */
    @Transactional
    public void signUp(UserSaveForm userForm){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Users users = Users.builder()
                .userId(userForm.getUserId())
                .userNm(userForm.getUserNm())
                .password(passwordEncoder.encode(userForm.getPassword()))
                .build();
        userRepository.save(users);
    }

    /**
     * 회원 중복 체크
     */
    public boolean validateDuplicateUsers(String userId) {
        return userRepository.findByUserId(userId).isEmpty(); //true=회원가입가능
    }
}
