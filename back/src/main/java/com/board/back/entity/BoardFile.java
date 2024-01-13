package com.board.back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
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
}
