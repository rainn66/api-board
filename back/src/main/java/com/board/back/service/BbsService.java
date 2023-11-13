package com.board.back.service;

import com.board.back.domain.BbsCategoryEntity;
import com.board.back.domain.BbsMainEntity;
import com.board.back.dto.BbsCategoryDto;
import com.board.back.dto.BbsMainDto;
import com.board.back.repository.BbsMainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BbsService {

    private final BbsMainRepository bbsMainRepository;

    /**
     * 게시글 목록 가져오기
     */
    public List<BbsMainDto> getBbsMainList(String bbsCategoryCd) {
        List<BbsMainEntity> BbsMainEntities = bbsMainRepository.findByBbsCategoryCd(bbsCategoryCd);
        List<BbsMainDto> bbsMainList = new ArrayList<>();

        for (BbsMainEntity entity : BbsMainEntities) {
            BbsMainDto dto = BbsMainDto.builder()
                .bbsIdx(entity.getBbsIdx())
                .bbsCategoryCd(entity.getBbsCategoryCd())
                .bbsTitle(entity.getBbsTitle())
                .bbsContents(entity.getBbsContents())
                .bbsTopFixYn(entity.getBbsTopFixYn())
                .delYn(entity.getDelYn())
                .regUserId(entity.getRegUserId())
                .regDt(entity.getRegDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .build();

            bbsMainList.add(dto);
        }

        return bbsMainList;
    }
}
