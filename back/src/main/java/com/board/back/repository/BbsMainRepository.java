package com.board.back.repository;

import com.board.back.domain.BbsMainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BbsMainRepository extends JpaRepository<BbsMainEntity, Long>, BbsMainRepositoryCustom {
}
