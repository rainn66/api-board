package com.board.back.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse {

    private int code;
    private String title;
    private String message;

    public ErrorResponse(ErrorForm errorForm) {
        this.code = errorForm.getCode();
        this.message = errorForm.getMessage();
    }

    public ErrorResponse(int code, String title, String message) {
        this.code = code;
        this.title = title;
        this.message = message;
    }

    public static ErrorResponse of(ErrorForm errorForm) {
        return new ErrorResponse(errorForm);
    }

    public static ErrorResponse set(int code, String title, String message) {
        return new ErrorResponse(code, title, message);
    }
}
