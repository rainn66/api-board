package com.board.back.controller;

import com.board.back.form.validation.BoardSaveForm;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardControllerTest {


    @BeforeAll
    static void setUp() {
    }

    @Test
    @DisplayName("@Size @NotBlank")
    void validationTest(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        BoardSaveForm saveForm = new BoardSaveForm(1L,
                "50글자테스트중입니다50글자테스트중입니다50글자테스트중입니다50글자테스트중입니다50글자테스트중입니다50글자테스트중입니다",
                "",
                "N");


    }

}