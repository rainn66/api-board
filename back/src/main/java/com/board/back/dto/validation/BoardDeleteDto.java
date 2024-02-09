package com.board.back.dto.validation;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardDeleteDto {

    @NotNull
    private Long boardIdx;

    @NotEmpty
    private String regUserId;

    private List<Long> boardFileIdxList;
}
