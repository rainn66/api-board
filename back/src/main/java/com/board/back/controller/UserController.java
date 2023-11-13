//package com.board.back.controller;
//
//import com.board.back.dto.UserDto;
//import com.board.back.service.UserService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequiredArgsConstructor
//@ResponseBody
//public class UserController {
//
//    private final UserService userService;
//
//    @PostMapping("/signup")
//    public void signUpUser(@RequestBody @Valid UserDto userForm) throws Exception {
//        userService.signUpUser(userForm);
//    }
//
//}
