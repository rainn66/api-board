package com.board.back.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "bbs_main")
public class BbsMainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
