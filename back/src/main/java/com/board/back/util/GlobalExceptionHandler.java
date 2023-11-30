package com.board.back.util;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JsMessageException.class)
    public void jsMessage(JsMessageException jsMessageException, Model model) {
        model.addAttribute("errMsg : ", jsMessageException.getMessage());
    }

}
