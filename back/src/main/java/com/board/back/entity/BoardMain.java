package com.board.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
public class BoardMain extends BaseEntity {

    @Id
    @GeneratedValue
    private Long boardMainIdx;

    private String boardNm;

    private String useYn;

    public BoardMain(String boardNm, String useYn) {
        this.boardNm = boardNm;
        this.useYn = useYn;
    }

    public BoardMain() {

    }
}
