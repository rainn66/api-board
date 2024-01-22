package com.board.back.controller;

import com.board.back.form.validation.BoardSaveForm;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

class BoardControllerTest {

    @Test
    @DisplayName("@Size @NotBlank")
    void validationTest(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        BoardSaveForm saveForm = new BoardSaveForm(1L,
                "50글자테스트중입니다50글자테스트중입니다50글자테스트중입니다50글자테스트중입니다50글자테스트중입니다50글자테스트중입니다",
                "",
                "N");
        Set<ConstraintViolation<BoardSaveForm>> violations = validator.validate(saveForm);
        for (ConstraintViolation<BoardSaveForm> violation : violations) {
            System.out.println("violation = " + violation);
        }

    }

}