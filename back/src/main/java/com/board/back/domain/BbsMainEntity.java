package com.board.back.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "bbs_main")
public class BbsMainEntity extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "bbs_idx")
    private Long bbsIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bbs_category_id")
    private BbsCategoryEntity bbsCategoryEntity;

    private String bbsTitle;

    private String bbsContents;

    private String bbsTopFixYn;

    private String delYn;
}
