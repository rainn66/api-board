package com.board.back.form.validation;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginForm {

    @NotEmpty
    private String userId;

    @NotEmpty
    private String password;
}
