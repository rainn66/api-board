package com.board.back.repository;

import com.board.back.entity.BoardMain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardMainRepository extends JpaRepository<BoardMain, Long> {

    BoardMain findByBoardMainIdx(Long boardMainIdx);
}
