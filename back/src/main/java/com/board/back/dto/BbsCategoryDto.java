package com.board.back.dto;

import com.board.back.domain.BbsCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BbsCategoryDto {

    private BbsCategoryEntity bbsCategoryCd;
    private String bbsCategoryCdNm;
    private String useYn;
    private String regUserId;
    private String regDt;
    private String chgUserId;
    private String chgDt;

}
