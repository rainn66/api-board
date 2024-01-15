package com.board.back.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
public class UserDto {
    private Long userIdx;
    private String userId;
    private String userNm;
    private String password;
    private LocalDateTime lastLoginDt;
    private LocalDateTime regDt;

    @QueryProjection
    public UserDto(String userId, String userNm, String password) {
        this.userId = userId;
        this.userNm = userNm;
        this.password = password;
    }
}
