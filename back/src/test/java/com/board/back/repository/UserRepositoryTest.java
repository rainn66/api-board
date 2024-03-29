package com.board.back.repository;

import com.board.back.entity.User;
import com.board.back.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Rollback(false)
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @DisplayName("1. 유저 데이터 생성하기")
    @Test
    void test_1(){
        String encPassword = passwordEncoder.encode("test!");
        User userEntity = User.builder()
                .userId("test")
                .userNm("테스트유저")
                .password(encPassword)
                .lastLoginDt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(userEntity);
        assertThat(userEntity.getUserId()).isEqualTo(savedUser.getUserId());
    }

    //@DisplayName("2. 유저정보 검색 후 비밀번호 비교")
    //@Test
    //void test_2(){
    //
    //    String userId = "test_user";
    //    String userPassword = "test_password";
    //
    //    UserDetails user = userService.loadUserByUsername(userId);
    //    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, userPassword);
    //    authenticationManager.authenticate(authenticationToken);
    //
    //    assertThat(authenticationToken.getCredentials()).isEqualTo(userPassword);
    //
    //    System.out.println("getCredentials: " + authenticationToken.getCredentials());
    //    System.out.println("userPw: " + userPassword);
    //}

}