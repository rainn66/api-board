package com.board.back.service;

import com.board.back.domain.BbsMainEntity;
import com.board.back.dto.BbsMainDto;
import com.board.back.model.Header;
import com.board.back.model.Pagination;
import com.board.back.repository.BbsMainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BbsService {

    private final BbsMainRepository bbsMainRepository;

    /**
     * 게시글 목록
     */
    public Header<List<BbsMainDto>> getBbsMainList(String bbsCategoryCd, Pageable pageable) {
        Page<BbsMainEntity> bbsMainEntities = bbsMainRepository.findByBbsCategoryCdAndDelYn(bbsCategoryCd, "N", pageable);
        List<BbsMainDto> bbsMainList = new ArrayList<>();

        for (BbsMainEntity entity : bbsMainEntities) {
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

        Pagination pagination = new Pagination(
            (int) bbsMainEntities.getTotalElements()
            , pageable.getPageNumber() + 1
            , pageable.getPageSize()
            , 10
        );

        return Header.OK(bbsMainList, pagination);
    }

    /**
     * 게시글 상세
     */
    public BbsMainDto getBbsMainInfo(Long bbsIdx) {
        BbsMainEntity entity = bbsMainRepository.findByBbsIdx(bbsIdx);
        BbsMainDto bbsMainForm;

        bbsMainForm = BbsMainDto.builder()
            .bbsIdx(entity.getBbsIdx())
            .bbsCategoryCd(entity.getBbsCategoryCd())
            .bbsTitle(entity.getBbsTitle())
            .bbsContents(entity.getBbsContents())
            .regUserId(entity.getRegUserId())
            .regDt(entity.getRegDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
            .build();

        return bbsMainForm;
    }

    /**
     * 게시글 등록
     */
    public BbsMainEntity regBbsMainInfo(BbsMainDto bbsMainDto) {
        BbsMainEntity entity = new BbsMainEntity();
        entity.setBbsCategoryCd(bbsMainDto.getBbsCategoryCd());
        entity.setBbsTitle(bbsMainDto.getBbsTitle());
        entity.setBbsContents(bbsMainDto.getBbsContents());
        entity.setBbsTopFixYn(bbsMainDto.getBbsTopFixYn());
        entity.setDelYn("N");
        entity.setRegDt(LocalDateTime.now());
        entity.setRegUserId(bbsMainDto.getRegUserId());
        return bbsMainRepository.save(entity);
    }

    /**
     * 게시글 수정
     */
    public BbsMainEntity modBbsMainInfo(BbsMainDto bbsMainDto) {
        BbsMainEntity entity = bbsMainRepository.findById(bbsMainDto.getBbsIdx()).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        entity.setBbsTitle(bbsMainDto.getBbsTitle());
        entity.setBbsContents(bbsMainDto.getBbsContents());
        entity.setChgDt(LocalDateTime.now());
        entity.setChgUserId(bbsMainDto.getRegUserId());
        return bbsMainRepository.save(entity);
    }

    /**
     * 게시글 삭제
     */
    public BbsMainEntity delBbsMainInfo(BbsMainDto bbsMainDto) {
        BbsMainEntity entity = bbsMainRepository.findById(bbsMainDto.getBbsIdx()).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        entity.setDelYn("Y");
        entity.setChgDt(LocalDateTime.now());
        entity.setChgUserId(bbsMainDto.getRegUserId());
        return bbsMainRepository.save(entity);
    }

}
