package com.board.back.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="users")
@Getter
public class UserEntity extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "user_idx")
    private String userIdx;

    @Column(name = "user_id")
    private String userId;

    private String userName;

    private String userPassword;
}
