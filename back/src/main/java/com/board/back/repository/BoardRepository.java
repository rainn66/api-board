package com.board.back.repository;

import com.board.back.dto.BoardDto;
import com.board.back.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
    Board findByBoardIdx(Long boardIdx);
}
