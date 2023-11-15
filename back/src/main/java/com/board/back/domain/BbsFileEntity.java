package com.board.back.domain;//package com.board.back.domain;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@Table(name="bbs_file")
//public class BbsFile {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long bbsFileIdx;
//
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn
//    private BbsMain bbsIdx;
//
//    private String fileOrgNm;
//
//    private String fileSaveNm;
//
//    private String delYn;
//
//    private String regUserId;
//
//    private LocalDateTime regDt;
//
//}
