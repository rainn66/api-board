package com.board.back.util;

import com.board.back.form.ErrorForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorForm> runtimeExceptionHandler(RuntimeException e) {
        ErrorForm errorForm = new ErrorForm("FAIL", e.getMessage());
        return new ResponseEntity<>(errorForm, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorForm> exceptionHandler(Exception e) {
        ErrorForm errorForm = new ErrorForm("FAIL", e.getMessage());
        return new ResponseEntity<>(errorForm, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
