package com.board.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardMain extends BaseEntity {

    @Id
    @GeneratedValue
    private Long boardMainIdx;

    private String boardNm;

    private String useYn;
}
