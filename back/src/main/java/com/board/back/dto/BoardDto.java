package com.board.back.dto;

import com.board.back.entity.BoardFile;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardDto {

    private Long boardIdx;
    private Long boardMainIdx;
    private String boardTitle;
    private String boardContent;
    private String topFixYn;
    private String delYn;
    private String regUserId;

    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDt;

    private List<BoardFile> boardFiles;

    @QueryProjection
    public BoardDto(Long boardIdx, Long boardMainIdx, String boardTitle, String boardContent, String topFixYn, String delYn, String regUserId, LocalDateTime regDt) {
        this.boardIdx = boardIdx;
        this.boardMainIdx = boardMainIdx;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.topFixYn = topFixYn;
        this.delYn = delYn;
        this.regUserId = regUserId;
        this.regDt = regDt;
    }

}
