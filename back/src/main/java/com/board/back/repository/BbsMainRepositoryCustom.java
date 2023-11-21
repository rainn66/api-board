package com.board.back.repository;

import com.board.back.domain.BbsMainEntity;
import com.board.back.model.SearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.board.back.domain.QBbsMainEntity.bbsMainEntity;

@RequiredArgsConstructor
@Repository
public class BbsMainRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public Page<BbsMainEntity> findByCategoryCdAndDelYnSearchCondition(Pageable pageable, SearchCondition searchCondition, String bbsCategoryCd, String delYn) {

        //TODO: 이방법은 쿼리를 두번 조회하게 됨(수정필요)
        JPAQuery<BbsMainEntity> query = queryFactory.selectFrom(bbsMainEntity)
            .where(searchKeywords(searchCondition.getSearchKey(), searchCondition.getSearchVal())
                , bbsMainEntity.bbsCategoryCd.eq(bbsCategoryCd)
                , bbsMainEntity.delYn.eq(delYn));

        long total = query.stream().count();

        List<BbsMainEntity> results = query
            .where(searchKeywords(searchCondition.getSearchKey(), searchCondition.getSearchVal())
                , bbsMainEntity.bbsCategoryCd.eq(bbsCategoryCd)
                , bbsMainEntity.delYn.eq(delYn))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(bbsMainEntity.bbsIdx.desc())
            .fetch();

        return new PageImpl<>(results, pageable, total);
    }

    private BooleanExpression searchKeywords(String searchKey, String searchVal) {
        if("regUserId".equals(searchKey)) {
            if(StringUtils.hasLength(searchVal)) {
                return bbsMainEntity.regUserId.contains(searchVal);
            }
        } else if ("bbsTitle".equals(searchKey)) {
            if(StringUtils.hasLength(searchVal)) {
                return bbsMainEntity.bbsTitle.contains(searchVal);
            }
        } else if ("bbsContents".equals(searchKey)) {
            if(StringUtils.hasLength(searchVal)) {
                return bbsMainEntity.bbsContents.contains(searchVal);
            }
        }

        return null;
    }
}