package com.board.back.dto.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardUpdateDto {

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

    private List<Long> boardFileIdxList;
}
