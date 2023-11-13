package com.board.back.dto;

import com.board.back.domain.BbsCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BbsMainDto implements Serializable {

    private Long bbsIdx;
    private String bbsCategoryCd;
    private String bbsTitle;
    private String bbsContents;
    private String bbsTopFixYn;
    private String delYn;
    private String regUserId;
    private String regDt;
    private String chgUserId;
    private String chgDt;

}
