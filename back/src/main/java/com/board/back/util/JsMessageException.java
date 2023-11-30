package com.board.back.util;

public class JsMessageException extends RuntimeException {
    public JsMessageException() {
        super("RuntimeException");
    }
    public JsMessageException(String msg) {
        super(msg);
    }

}
