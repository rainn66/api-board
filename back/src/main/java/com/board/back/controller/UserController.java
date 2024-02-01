package com.board.back.controller;

import com.board.back.form.validation.UserLoginForm;
import com.board.back.form.validation.UserSaveForm;
import com.board.back.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081", exposedHeaders = "token")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginForm userForm,
                                                     BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            FieldError error = bindingResult.getFieldErrors().get(0);
            throw new Exception(error.getDefaultMessage());
        }
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("Authorization", userService.login(userForm));
        return ResponseEntity.ok().headers(responseHeader).body("Not null");
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody @Valid UserSaveForm userForm,
                                                      BindingResult bindingResult) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (bindingResult.hasErrors()) {
            FieldError error = bindingResult.getFieldErrors().get(0);
            throw new Exception(error.getDefaultMessage());
        }
        if (userService.validateDuplicateUsers(userForm.getUserId())) {
            String userNm = userService.signUp(userForm);
            result.put("userNm", userNm);
        } else {
            throw new Exception("이미 존재하는 회원입니다.");
        }
        return ResponseEntity.ok(result);
    }
}
