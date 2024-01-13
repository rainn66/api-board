package com.board.back.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
public class BoardMain extends BaseEntity {

    @Id
    @GeneratedValue
    private Long boardMainIdx;

    private String boardCdNm;

    private String useYn;

    public BoardMain(String boardCdNm, String useYn) {
        this.boardCdNm = boardCdNm;
        this.useYn = useYn;
    }

    public BoardMain() {

    }
}
