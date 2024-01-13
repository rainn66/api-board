package com.board.back.repository;

import com.board.back.entity.Board;
import com.board.back.entity.BoardMain;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(false)
@Transactional
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardMainRepository boardMainRepository;

    @Test
    public void boardMainTest() {

        BoardMain boardMain = new BoardMain("자유게시판", "Y");
        BoardMain savedBoardMain = boardMainRepository.save(boardMain);

        BoardMain findBoardMain = boardMainRepository.findByBoardMainIdx(savedBoardMain.getBoardMainIdx());
        Assertions.assertThat(boardMain).isEqualTo(findBoardMain);

        Board board = new Board(findBoardMain, "게시판제목", "게시판내용", "N", "N");
        Board savedBoard = boardRepository.save(board);
        Assertions.assertThat(board).isEqualTo(savedBoard);
    }

}