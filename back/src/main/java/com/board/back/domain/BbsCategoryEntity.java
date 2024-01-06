package com.board.back.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="bbs_category")
public class BbsCategoryEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Long bbsCategoryId;

    private String bbsCategoryCd;

    private String bbsCategoryCdNm;

    private String useYn;
}
