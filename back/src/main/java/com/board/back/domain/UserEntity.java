package com.board.back.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="user")
public class UserEntity {

    @Id
    private String userId;

    private String userNm;

    private String userPassword;

    private LocalDateTime regDt;

}
