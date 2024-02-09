package com.board.back.dto.validation;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class BoardFileDeleteDto {
    @NotNull
    private Long boardFileIdx;

    @NotEmpty
    private String regUserId;
}
