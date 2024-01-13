package com.board.back;

import com.board.back.entity.Board;
import com.board.back.entity.BoardMain;
import com.board.back.repository.BoardMainRepository;
import com.board.back.repository.BoardRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final BoardRepository boardRepository;

    private final BoardMainRepository boardMainRepository;

    @PostConstruct
    public void init() {

        BoardMain boardMain = new BoardMain("자유게시판", "Y");
        BoardMain savedBoardMain = boardMainRepository.save(boardMain);

        BoardMain findBoardMain = boardMainRepository.findByBoardMainIdx(savedBoardMain.getBoardMainIdx());
        for (int i = 0; i < 24; i++) {
            Board board = Board.builder()
                    .boardMain(findBoardMain)
                    .boardTitle("게시판제목" + i)
                    .boardContent("게시판내용" + i)
                    .topFixYn("N")
                    .delYn("N")
                    .build();
            boardRepository.save(board);
        }
    }


}
