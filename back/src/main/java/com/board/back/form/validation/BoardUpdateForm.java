package com.board.back.form.validation;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class BoardUpdateForm {

    @NotNull
    private Long boardIdx;

    @NotNull
    private Long boardMainIdx;

    @NotBlank
    @Size(max = 50)
    private String boardTitle;

    private String boardContent;

    @NotEmpty
    private String topFixYn;

    @NotEmpty
    private String regUserId;
}
