package com.board.back.form.validation;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class BoardFileDeleteForm {
    @NotNull
    private Long boardFileIdx;

    @NotEmpty
    private String regUserId;
}
