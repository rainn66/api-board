package com.board.back.form;

import lombok.Getter;

@Getter
public enum ErrorForm {

    EXPIRED_TOKEN(403, "사용 기간이 만료되었습니다. 다시 로그인 해주세요."),
    INTERNAL_SEVER_ERROR(500, "unknown server error");

    private final int code;
    private final String message;

    ErrorForm(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
