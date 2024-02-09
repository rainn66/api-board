package com.board.back.dto.validation;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {

    @NotEmpty
    private String userId;

    @NotEmpty
    private String password;
}
