package com.board.back.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BbsMainDto {

    private Long bbsIdx;
    private String bbsCategoryCd;
    private String bbsTitle;
    private String bbsContents;
    private String bbsTopFixYn;
    private String delYn;
    private String regUserId;
    private LocalDateTime regDt;
    private String chgUserId;
    private LocalDateTime chgDt;

    @QueryProjection
    public BbsMainDto(Long bbsIdx, String bbsCategoryCd, String bbsTitle, String bbsContents, String bbsTopFixYn, String delYn, String regUserId, LocalDateTime regDt, String chgUserId, LocalDateTime chgDt) {
        this.bbsIdx = bbsIdx;
        this.bbsCategoryCd = bbsCategoryCd;
        this.bbsTitle = bbsTitle;
        this.bbsContents = bbsContents;
        this.bbsTopFixYn = bbsTopFixYn;
        this.delYn = delYn;
        this.regUserId = regUserId;
        this.regDt = regDt;
        this.chgUserId = chgUserId;
        this.chgDt = chgDt;
    }
}
