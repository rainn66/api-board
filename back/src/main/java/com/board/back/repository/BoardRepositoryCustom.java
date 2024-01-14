package com.board.back.repository;

import com.board.back.dto.BoardDto;
import com.board.back.model.BoardSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {
    Page<BoardDto> search(BoardSearchCondition condition, Pageable pageable, Long boardMainIdx);
}