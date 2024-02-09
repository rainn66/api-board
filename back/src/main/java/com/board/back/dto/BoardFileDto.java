package com.board.back.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardFileDto {

    private String fileOrgNm;

    private String fileSaveNm;

    private String fileSavePath;

    public BoardFileDto(String fileOrgNm, String fileSaveNm, String fileSavePath) {
        this.fileOrgNm = fileOrgNm;
        this.fileSaveNm = fileSaveNm;
        this.fileSavePath = fileSavePath;
    }
}
