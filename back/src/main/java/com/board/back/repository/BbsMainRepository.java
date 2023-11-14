package com.board.back.repository;

import com.board.back.domain.BbsMainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BbsMainRepository extends JpaRepository<BbsMainEntity, Long> {

    List<BbsMainEntity> findByBbsCategoryCdAndDelYn(String bbsCategoryCd, String delYn);

    BbsMainEntity findByBbsIdx(Long bbsIdx);
}
