package com.board.back.controller;

import com.board.back.exception.Exception400;
import com.board.back.dto.validation.UserLoginDto;
import com.board.back.dto.validation.UserSaveDto;
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
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginDto userForm,
                                                     BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            FieldError error = bindingResult.getFieldErrors().get(0);
            throw new Exception400(error.getDefaultMessage());
        }
        HttpHeaders responseHeader = new HttpHeaders();
        String jwt = userService.login(userForm);
        responseHeader.add("Authorization", jwt);
        return ResponseEntity.ok().headers(responseHeader).body("Success, Check headers jwt");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid UserSaveDto userForm,
                                                      BindingResult bindingResult) throws Exception {

        Map<String, Object> result = new HashMap<>();
        if (bindingResult.hasErrors()) {
            FieldError error = bindingResult.getFieldErrors().get(0);
            throw new Exception400(error.getDefaultMessage());
        }
        String userNm = userService.signUp(userForm);
        result.put("userNm", userNm);

        return ResponseEntity.ok(result);
    }
}
