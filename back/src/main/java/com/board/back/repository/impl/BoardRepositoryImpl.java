package com.board.back.repository.impl;

import com.board.back.dto.BoardDto;
import com.board.back.dto.QBoardDto;
import com.board.back.form.condition.BoardSearchCondition;
import com.board.back.repository.BoardRepositoryCustom;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.board.back.entity.QBoard.*;
import static com.board.back.entity.QBoardMain.*;

public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<BoardDto> search(BoardSearchCondition condition, Pageable pageable, Long boardMainIdx) {

        List<BoardDto> result = jpaQueryFactory
                .select(new QBoardDto(
                        board.boardIdx,
                        board.boardMain.boardMainIdx,
                        board.boardTitle,
                        board.boardContent,
                        board.topFixYn,
                        board.regUserId,
                        board.regDt
                        )
                )
                .from(board)
                .leftJoin(board.boardMain, boardMain)
                .where(boardTitleEq(condition.getBoardTitle()),
                        boardContentEq(condition.getBoardContent()),
                        regUserIdEq(condition.getRegUserId()),
                        board.delYn.eq( "N"),
                        boardMain.boardMainIdx.eq(boardMainIdx))
                .orderBy(board.boardIdx.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = jpaQueryFactory
                .select(board.count())
                .from(board)
                .leftJoin(board.boardMain, boardMain)
                .where(boardTitleEq(condition.getBoardTitle()),
                        boardContentEq(condition.getBoardContent()),
                        regUserIdEq(condition.getRegUserId()),
                        board.delYn.eq( "N"),
                        boardMain.boardMainIdx.eq(boardMainIdx))
                .fetchOne();

        return new PageImpl<>(result, pageable, total);
    }

    private Predicate boardTitleEq(String boardTitle) {
        return StringUtils.hasText(boardTitle) ? board.boardTitle.contains(boardTitle) : null;
    }

    private Predicate boardContentEq(String boardContent) {
        return StringUtils.hasText(boardContent) ? board.boardContent.contains(boardContent) : null;
    }

    private Predicate regUserIdEq(String regUserId) {
        return StringUtils.hasText(regUserId) ? board.regUserId.contains(regUserId) : null;
    }



}
