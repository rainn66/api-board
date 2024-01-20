package com.board.back.form.validation;

import lombok.Getter;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Getter
public class BoardDeleteForm {

    @NotNull
    private Long boardIdx;

    @NotEmpty
    private String regUserId;
}
