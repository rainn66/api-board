package com.board.back.dto.condition;

import lombok.Data;

@Data
public class BoardSearchConditionDto {

    private String searchKey;
    private String searchVal;

    private String regUserId;
    private String boardTitle;
    private String boardContent;

    public BoardSearchConditionDto(String searchKey, String searchVal) {
        if ("regUserId".equals(searchKey)) {
            this.regUserId = searchVal;
        } else if ("boardTitle".equals(searchKey)) {
            this.boardTitle = searchVal;
        } else {
            this.boardContent = searchVal;
        }
    }
}