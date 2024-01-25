package com.board.back.form;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardFileForm {

    private String fileOrgNm;

    private String fileSaveNm;

    private String fileSavePath;

    public BoardFileForm(String fileOrgNm, String fileSaveNm, String fileSavePath) {
        this.fileOrgNm = fileOrgNm;
        this.fileSaveNm = fileSaveNm;
        this.fileSavePath = fileSavePath;
    }
}
