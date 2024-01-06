package com.board.back.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="bbs_file")
public class BbsFileEntity {

    @Id
    @GeneratedValue
    @Column(name = "bbs_file_idx")
    private Long bbsFileIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bbs_idx")
    private BbsMainEntity bbsMainEntity;

    private String fileOrgNm;

    private String fileSaveNm;

    private String delYn;
}
