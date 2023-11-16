package com.board.back.repository;

import com.board.back.domain.BbsMainEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

public interface BbsMainRepository extends JpaRepository<BbsMainEntity, Long> {

    //Page<BbsMainEntity> findByBbsCategoryCdAndDelYn(Pageable pageable, String bbsCategoryCd, String delYn);

    BbsMainEntity findByBbsIdx(Long bbsIdx);

    //Page<BbsMainEntity> findAllByOrderByBbsIdxDesc(Pageable pageable);
}
