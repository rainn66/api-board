package com.board.back.repository.impl;

import com.board.back.dto.BbsMainDto;
import com.board.back.dto.QBbsMainDto;
import com.board.back.model.BbsSearchCondition;
import com.board.back.repository.BbsMainRepositoryCustom;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.board.back.domain.QBbsCategoryEntity.bbsCategoryEntity;
import static com.board.back.domain.QBbsMainEntity.bbsMainEntity;

@RequiredArgsConstructor
public class BbsMainRepositoryImpl implements BbsMainRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<BbsMainDto> searchPageComplex(BbsSearchCondition condition, Pageable pageable, String bbsCategoryCd) {

        List<BbsMainDto> result = jpaQueryFactory
                .select(new QBbsMainDto(
                        bbsMainEntity.bbsIdx,
                        bbsMainEntity.bbsCategoryEntity.bbsCategoryCd,
                        bbsMainEntity.bbsTitle,
                        bbsMainEntity.bbsContents,
                        bbsMainEntity.bbsTopFixYn,
                        bbsMainEntity.delYn,
                        bbsMainEntity.regUserId,
                        bbsMainEntity.regDt,
                        bbsMainEntity.chgUserId,
                        bbsMainEntity.chgDt
                ))
                .from(bbsMainEntity)
                .leftJoin(bbsMainEntity.bbsCategoryEntity, bbsCategoryEntity)
                .where(bbsTitleEq(condition.getBbsTitle()),
                        regUserIdEq(condition.getRegUserId()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = jpaQueryFactory
                .select(bbsMainEntity.count())
                .from(bbsMainEntity)
                .leftJoin(bbsMainEntity.bbsCategoryEntity, bbsCategoryEntity)
                .where(bbsTitleEq(condition.getBbsTitle()),
                        regUserIdEq(condition.getRegUserId()))
                .fetchOne();

        return new PageImpl<>(result, pageable, total);
    }

    private Predicate bbsTitleEq(String bbsTitle) {
        return StringUtils.hasText(bbsTitle) ? bbsMainEntity.bbsTitle.eq(bbsTitle) : null;
    }

    private Predicate regUserIdEq(String regUserId) {
        return StringUtils.hasText(regUserId) ? bbsMainEntity.regUserId.eq(regUserId) : null;
    }



}
