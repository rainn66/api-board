package com.board.back.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Exception400 extends RuntimeException{

    public Exception400(String message) {
        super(message);
    }

    public ErrorResponse body() {
        return ErrorResponse.set(400, null, getMessage());
    }

    public HttpStatus status() {
        return HttpStatus.BAD_REQUEST;
    }
}
