package com.board.back.controller;

import com.board.back.domain.BbsMainEntity;
import com.board.back.dto.BbsMainDto;
import com.board.back.model.Header;
import com.board.back.model.SearchCondition;
import com.board.back.service.BbsService;
import com.board.back.service.UserService;
import com.board.back.util.JwtUtil;
import com.board.back.util.TokenRequestFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class BbsController {
    private final UserService userService;
    private final BbsService bbsService;
    private final JwtUtil jwtUtil;
    private final TokenRequestFilter tokenRequestFilter;

    @GetMapping("/bbsMainList")
    public Header<List<BbsMainDto>> bbsMainList(
        @PageableDefault(sort = {"bbsIdx"}, direction = Sort.Direction.DESC) Pageable pageable
        , SearchCondition searchCondition
        , @RequestParam(value = "bbsCategoryCd") String bbsCategoryCd) {
        return bbsService.getBbsMainList(pageable, searchCondition, bbsCategoryCd);
    }

    @GetMapping("/bbsMainInfo/{bbsIdx}")
    public BbsMainDto bbsMainInfo(@PathVariable Long bbsIdx) {
        return bbsService.getBbsMainInfo(bbsIdx);
    }

    @PostMapping("/bbsMainExec/{mode}")
    public BbsMainEntity bbsMainExec(HttpServletRequest request, @RequestBody BbsMainDto bbsMainDto, @PathVariable String mode) throws Exception {

        String jwtToken = tokenRequestFilter.parseJwt(request);

        String userId = jwtUtil.getUserFromToken(jwtToken);

        UserDetails userInfo = userService.loadUserByUsername(userId);

        log.debug("debug userId {}", userId);

        if ("R".equals(mode)) {
            return bbsService.regBbsMainInfo(bbsMainDto);
        } else if ("U".equals(mode)){
            if (!userInfo.getUsername().equals(bbsMainDto.getRegUserId())) {
                throw new Exception("글 수정 권한이 없습니다.");
            }
            return bbsService.modBbsMainInfo(bbsMainDto);
        } else {
            if (!userInfo.getUsername().equals(bbsMainDto.getRegUserId())) {
                throw new Exception("글 삭제 권한이 없습니다.");
            }
            return bbsService.delBbsMainInfo(bbsMainDto);
        }
    }

}
