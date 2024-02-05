package com.board.back.exception;

import org.springframework.http.HttpStatus;

public class Exception404 extends RuntimeException {
    public Exception404() {
    }

    public Exception404(String message) {
        super(message);
    }

    public ErrorResponse body() {
        if (super.getMessage() == null) {
            return ErrorResponse.of(ErrorForm.NOT_FOUND);
        } else {
            return ErrorResponse.set(404, null, super.getMessage());
        }
    }

    public HttpStatus status() {
        return HttpStatus.NOT_FOUND;
    }
}
