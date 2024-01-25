package com.board.back.service;

import com.board.back.dto.UserDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    /**
     * 회원 확인, UserDetail 세팅
     */
    User loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * 회원 가입
     */
    void signUp(UserDto userDto);

    /**
     * 회원 중복 체크
     */
    boolean validateDuplicateUsers(UserDto userDto);

    /**
     * 마지막 접속일 갱신
     */
    void updateLoginDt(User loginUser);
}
