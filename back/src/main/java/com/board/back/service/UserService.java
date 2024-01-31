package com.board.back.service;

import com.board.back.form.validation.UserSaveForm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    /**
     * 회원 확인, UserDetail 세팅
     */
    UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException;

    /**
     * 회원 가입
     */
    void signUp(UserSaveForm userForm);

    /**
     * 회원 중복 체크
     */
    boolean validateDuplicateUsers(String userId);
}
