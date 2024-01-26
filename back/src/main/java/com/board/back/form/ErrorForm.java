package com.board.back.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorForm {

    private String errorCode;
    private String message;
}
