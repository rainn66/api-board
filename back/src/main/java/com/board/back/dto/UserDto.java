package com.board.back.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class UserDto {
    private Long userIdx;
    private String userId;
    private String userName;

    @QueryProjection
    public UserDto(Long userIdx, String userId, String userName) {
        this.userIdx = userIdx;
        this.userId = userId;
        this.userName = userName;
    }
}
