package com.board.back.domain;


import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="bbs_main")
public class BbsMain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bbsIdx;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bbs_category_cd")
    private BbsCategory bbsCategoryCd;

    private String bbsTitle;

    private String bbsContents;

    private String bbsTopFixYn;

    private String delYn;

    private String regUserId;

    private LocalDateTime regDt;

    private String chgUserId;

    private LocalDateTime chgDt;
}
