package com.board.back.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
//기본적으로 세자리 status 코드와 그 하위 상세 에러는 에러코드의 마지막 숫자로 관리
public enum ErrorForm {

    BAD_REQUEST(400, "잘못된 요청입니다."),
    UNAUTHORIZED(401, "권한이 없습니다."),
    NOT_FOUND(404, "찾을 수 없습니다."),
    INTERNAL_SEVER_ERROR(500, "알 수 없는 서버에러, 관리자에게 문의하세요.");

    private int code;
    private String message;

    ErrorForm(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
