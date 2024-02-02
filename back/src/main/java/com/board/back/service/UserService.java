package com.board.back.service;

import com.board.back.form.validation.UserLoginForm;
import com.board.back.form.validation.UserSaveForm;

public interface UserService {

    /**
     * 회원 확인, UserDetail 세팅
     */
    String login(UserLoginForm userForm);

    /**
     * 회원 가입
     */
    String signUp(UserSaveForm userForm);

    /**
     * 회원 중복 체크
     */
    boolean validateDuplicateUsers(String userId);
}
