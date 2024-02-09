package com.board.back.controller;

import com.board.back.dto.validation.BoardSaveDto;
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
        BoardSaveDto saveForm = new BoardSaveDto(1L,
                "50글자테스트중입니다50글자테스트중입니다50글자테스트중입니다50글자테스트중입니다50글자테스트중입니다50글자테스트중입니다",
                "",
                "N");
        Set<ConstraintViolation<BoardSaveDto>> violations = validator.validate(saveForm);
        for (ConstraintViolation<BoardSaveDto> violation : violations) {
            System.out.println("violation = " + violation);
        }

    }

}