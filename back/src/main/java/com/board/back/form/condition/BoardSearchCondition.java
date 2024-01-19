package com.board.back.form.condition;

import lombok.Data;

@Data
public class BoardSearchCondition {

    private String searchKey;
    private String searchVal;

    private String regUserId;
    private String boardTitle;
    private String boardContent;

    public BoardSearchCondition(String searchKey, String searchVal) {
        if ("regUserId".equals(searchKey)) {
            this.regUserId = searchVal;
        } else if ("boardTitle".equals(searchKey)) {
            this.boardTitle = searchVal;
        } else {
            this.boardContent = searchVal;
        }
    }
}