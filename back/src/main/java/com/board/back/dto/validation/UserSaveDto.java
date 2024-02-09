package com.board.back.dto.validation;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveDto {

    @NotEmpty
    private String userId;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String userNm;

    @NotEmpty
    private String password;
}
