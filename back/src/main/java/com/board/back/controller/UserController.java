package com.board.back.controller;

import com.board.back.dto.UserDto;
import com.board.back.service.UserService;
import com.board.back.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> paramMap) {
        String userId = paramMap.get("userId");
        String password = paramMap.get("password");

        //회원 조회
        UserDetails loginUser = userService.loadUserByUsername(userId);

        //가져온 정보와 입력한 비밀번호로 검증
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), password)
        );

        //스프링 시큐리티에 인증값 세팅
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //accessToken 생성
        String accessToken = jwtUtil.createJwt(loginUser.getUsername(), loginUser.getUsername());

        Map<String, Object> result = new HashMap<>();
        result.put("userId", loginUser.getUsername());
        result.put("userToken", accessToken);//회원 접속 고유 token
        result.put("userRole", loginUser.getAuthorities().stream().findFirst().get().getAuthority());

        return ResponseEntity.ok(result);
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody UserDto userDto) {

        Map<String, Object> result = new HashMap<>();
        if (userService.validateDuplicateUsers(userDto)) {
            userService.signUp(userDto);
            result.put("userNm", String.valueOf(userDto.getUserNm()));
            result.put("resultCd", "SUCCESS");
        } else {
            result.put("resultCd", "FAIL");
            result.put("msg", "이미 등록된 ID 입니다.");
            //return ResponseEntity.ofNullable(result);
        }
        return ResponseEntity.ok(result);
    }
}
