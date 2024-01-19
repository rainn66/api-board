package com.board.back.form.validation;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class BoardDeleteForm {

    @NotNull
    private Long boardIdx;

    @NotEmpty
    private String regUserId;
}
