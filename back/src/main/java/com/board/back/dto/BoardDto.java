package com.board.back.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardDto {

    private Long boardIdx;
    private Long boardMainIdx;
    private String boardTitle;
    private String boardContent;
    private String topFixYn;
    private String delYn;
    private String regUserId;
    private LocalDateTime regDt;

    @QueryProjection
    public BoardDto(Long boardIdx, Long boardMainIdx, String boardTitle, String boardContent, String topFixYn, String regUserId, LocalDateTime regDt) {
        this.boardIdx = boardIdx;
        this.boardMainIdx = boardMainIdx;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.topFixYn = topFixYn;
        this.regUserId = regUserId;
        this.regDt = regDt;
    }
}
