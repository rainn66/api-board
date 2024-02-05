package com.board.back.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Exception401 extends RuntimeException{

    private String title;

    public Exception401() {
    }

    public Exception401(String message) {
        super(message);
    }

    public Exception401(String title, String message) {
        super(message);
        this.title = title;
    }

    public ErrorResponse body() {
        if (super.getMessage() == null) {
            return ErrorResponse.of(ErrorForm.UNAUTHORIZED);
        } else {
            return ErrorResponse.set(401, title, super.getMessage());
        }
    }

    public HttpStatus status() {
        return HttpStatus.UNAUTHORIZED;
    }
}
