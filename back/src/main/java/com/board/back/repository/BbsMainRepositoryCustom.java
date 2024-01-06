package com.board.back.repository;

import com.board.back.dto.BbsMainDto;
import com.board.back.model.BbsSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BbsMainRepositoryCustom {
    Page<BbsMainDto> searchPageComplex(BbsSearchCondition condition, Pageable pageable, String bbsCategoryCd);
}