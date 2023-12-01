package com.board.back.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BbsMainDto implements Serializable {

    private Long bbsIdx;
    private String bbsCategoryCd;
    private String bbsTitle;
    private String bbsContents;
    private String bbsTopFixYn;
    private String delYn;
    private String regUserId;
    private String regDt;
    private String chgUserId;
    private String chgDt;

    public Long getBbsIdx() {
        return bbsIdx;
    }

    public void setBbsIdx(Long bbsIdx) {
        this.bbsIdx = bbsIdx;
    }

    public String getBbsCategoryCd() {
        return bbsCategoryCd;
    }

    public void setBbsCategoryCd(String bbsCategoryCd) {
        this.bbsCategoryCd = bbsCategoryCd;
    }

    public String getBbsTitle() {
        return bbsTitle;
    }

    public void setBbsTitle(String bbsTitle) {
        this.bbsTitle = bbsTitle;
    }

    public String getBbsContents() {
        return bbsContents;
    }

    public void setBbsContents(String bbsContents) {
        this.bbsContents = bbsContents;
    }

    public String getBbsTopFixYn() {
        return bbsTopFixYn;
    }

    public void setBbsTopFixYn(String bbsTopFixYn) {
        this.bbsTopFixYn = bbsTopFixYn;
    }

    public String getDelYn() {
        return delYn;
    }

    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }

    public String getRegUserId() {
        return regUserId;
    }

    public void setRegUserId(String regUserId) {
        this.regUserId = regUserId;
    }

    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    public String getChgUserId() {
        return chgUserId;
    }

    public void setChgUserId(String chgUserId) {
        this.chgUserId = chgUserId;
    }

    public String getChgDt() {
        return chgDt;
    }

    public void setChgDt(String chgDt) {
        this.chgDt = chgDt;
    }
}
