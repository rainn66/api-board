package com.board.back.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.BindParam;

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

    public void update(String boardTitle, String boardContent, String topFixYn, String delYn) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.topFixYn = topFixYn;
        this.delYn = delYn;
    }

    public void delete(String delYn) {
        this.delYn = delYn;
    }
}
