package com.board.back.domain;


import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="bbs_category")
public class BbsCategory {

    @Id
    private String bbsCategoryCd;

    private String bbsCategoryCdNm;

    private String useYn;

    private String regUserId;

    private LocalDateTime regDt;

    private String chgUserId;

    private LocalDateTime chgDt;


}
