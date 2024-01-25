package com.board.back.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "board_idx")
    private Long boardIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_main_idx")
    private BoardMain boardMain;

    private String boardTitle;

    private String boardContent;

    private String topFixYn;

    private String delYn;

    @JsonIgnore
    @OneToMany(mappedBy = "board")
    private List<BoardFile> boardFiles;

    public void update(BoardMain boardMain, String boardTitle, String boardContent, String topFixYn) {
        this.boardMain = boardMain;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.topFixYn = topFixYn;
    }

    public void delete(String delYn) {
        this.delYn = delYn;
    }
}
