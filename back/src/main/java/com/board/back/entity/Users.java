package com.board.back.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="users")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "user_idx")
    private Long userIdx;

    @Column(name = "user_id")
    private String userId;

    private String userNm;

    private String password;

    private LocalDateTime lastLoginDt;

    public void updateLastLoginDt(LocalDateTime lastLoginDt) {
        this.lastLoginDt = lastLoginDt;
    }

}
