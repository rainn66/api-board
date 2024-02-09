package com.board.back.repository;

import com.board.back.dto.BoardDto;
import com.board.back.dto.condition.BoardSearchConditionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {
    Page<BoardDto> search(BoardSearchConditionDto condition, Pageable pageable, Long boardMainIdx);
}