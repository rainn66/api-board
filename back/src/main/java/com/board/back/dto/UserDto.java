package com.board.back.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDto {

    @NotEmpty(message="아이디는 필수 입니다.")
    @Size(max=20, message="아이디는 20자리를 초과할 수 없습니다.")
    private String userId;

    @NotEmpty(message="회원명은 필수 입니다.")
    @Size(max=20, message="회원명은 20자리를 초과할 수 없습니다.")
    private String userNm;

    @NotEmpty(message="패스워드는 필수 입니다.")
    @Size(max=100, message="패스워드는 100자리를 초과할 수 없습니다.")
    private String userPassword;

}
