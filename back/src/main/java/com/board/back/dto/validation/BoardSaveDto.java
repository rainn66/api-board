package com.board.back.dto.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardSaveDto {

    @NotNull
    private Long boardMainIdx;

    @NotBlank
    @Size(max = 50)
    private String boardTitle;

    private String boardContent;

    @NotEmpty
    private String topFixYn;
}
