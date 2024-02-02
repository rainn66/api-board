package com.board.back.util;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.board.back.form.ErrorForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorForm> runtimeExceptionHandler(RuntimeException e) {
        //임시
        return new ResponseEntity<>(ErrorForm.INTERNAL_SEVER_ERROR, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorForm> tokenExpiredHandler(TokenExpiredException e) {
        return new ResponseEntity<>(ErrorForm.EXPIRED_TOKEN, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<?> unknownExceptionHandler(Exception e) {
        Map<String, Object> errorResult = new HashMap<>();
        errorResult.put("message", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
