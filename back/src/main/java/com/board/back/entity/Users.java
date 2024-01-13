package com.board.back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name="users")
public class Users extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "user_idx")
    private String userIdx;

    @Column(name = "user_id")
    private String userId;

    private String userNm;

    private String password;

    private String lastLoginDt;

    public Users() {
    }

    public Users(String userId, String userNm, String password, String lastLoginDt) {
        this.userId = userId;
        this.userNm = userNm;
        this.password = password;
        this.lastLoginDt = lastLoginDt;
    }
}
