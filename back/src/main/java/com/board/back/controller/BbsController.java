package com.board.back.controller;

import com.board.back.domain.BbsCategoryEntity;
import com.board.back.domain.BbsMainEntity;
import com.board.back.dto.BbsCategoryDto;
import com.board.back.dto.BbsMainDto;
import com.board.back.service.BbsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class BbsController {
    private final BbsService bbsService;

    @GetMapping("/bbs/list")
    public List<BbsMainDto> bbsMainList(@RequestParam(value="bbsCategoryCd") String bbsCategoryCd) {
        return bbsService.getBbsMainList(bbsCategoryCd);
    }

    @GetMapping("/bbs/{bbsIdx}")
    public BbsMainDto bbsMainForm(@PathVariable Long bbsIdx) {
        return bbsService.getBbsMainForm(bbsIdx);
    }


}
