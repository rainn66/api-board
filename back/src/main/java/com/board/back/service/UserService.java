package com.board.back.service;

import com.board.back.dto.validation.UserLoginDto;
import com.board.back.dto.validation.UserSaveDto;

public interface UserService {

    /**
     * 회원 확인, UserDetail 세팅
     */
    String login(UserLoginDto userForm);

    /**
     * 회원 가입
     */
    String signUp(UserSaveDto userForm) throws Exception;
}
