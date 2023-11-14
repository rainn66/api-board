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
    public BbsMainDto bbsMainInfo(@PathVariable Long bbsIdx) {
        return bbsService.getBbsMainInfo(bbsIdx);
    }

    @PostMapping("/bbs/exec/{mode}")
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
