package com.board.back.controller;

import com.board.back.domain.BbsMainEntity;
import com.board.back.dto.BbsMainDto;
import com.board.back.model.Header;
import com.board.back.service.BbsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class BbsController {
    private final BbsService bbsService;

    @GetMapping("/bbsMainList")
    public Header<List<BbsMainDto>> bbsMainList(
        @PageableDefault(sort = {"bbsIdx"}, direction = Sort.Direction.DESC) Pageable pageable
        , @RequestParam(value = "bbsCategoryCd") String bbsCategoryCd) {
        return bbsService.getBbsMainList(bbsCategoryCd, pageable);
    }

    @GetMapping("/bbsMainInfo/{bbsIdx}")
    public BbsMainDto bbsMainInfo(@PathVariable Long bbsIdx) {
        return bbsService.getBbsMainInfo(bbsIdx);
    }

    @PostMapping("/bbsMainExec/{mode}")
    public BbsMainEntity bbsMainExec(@RequestBody BbsMainDto bbsMainDto, @PathVariable String mode) {

        if ("R".equals(mode)) {
            return bbsService.regBbsMainInfo(bbsMainDto);
        } else if ("U".equals(mode)){
            return bbsService.modBbsMainInfo(bbsMainDto);
        } else {
            return bbsService.delBbsMainInfo(bbsMainDto);
        }
    }

}
