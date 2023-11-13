package com.board.back.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="bbs_category")
public class BbsCategoryEntity {

    @Id
    private String bbsCategoryCd;

    private String bbsCategoryCdNm;

    private String useYn;

    private String regUserId;

    private LocalDateTime regDt;

    private String chgUserId;

    private LocalDateTime chgDt;


}
