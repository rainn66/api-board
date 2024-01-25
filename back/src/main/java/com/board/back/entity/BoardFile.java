package com.board.back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardFile {

    @Id
    @GeneratedValue
    @Column(name = "board_file_idx")
    private Long boardFileIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_idx")
    private Board board;

    private String fileOrgNm;

    private String fileSaveNm;

    private String fileSavePath;

    public void update(String fileOrgNm, String fileSaveNm, String fileSavePath) {
        this.fileOrgNm = fileOrgNm;
        this.fileSaveNm = fileSaveNm;
        this.fileSavePath = fileSavePath;
    }
}

