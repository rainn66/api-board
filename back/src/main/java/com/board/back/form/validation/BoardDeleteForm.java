package com.board.back.form.validation;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardDeleteForm {

    @NotNull
    private Long boardIdx;

    @NotEmpty
    private String regUserId;

    private List<Long> boardFileIdxList;
}