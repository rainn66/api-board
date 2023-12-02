package com.board.back.controller;

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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> bbsMainExec(HttpServletRequest request, @RequestBody BbsMainDto bbsMainDto, @PathVariable String mode) throws Exception {

        Map<String, Object> result = new HashMap<>();

        try {
            //token 작성자 확인
            String jwtToken = tokenRequestFilter.parseJwt(request);
            String userId = jwtUtil.getUserFromToken(jwtToken);
            UserDetails userInfo = userService.loadUserByUsername(userId);

            if ("R".equals(mode)) {
                bbsMainDto.setRegUserId(userId);//token 작성자 바인딩
                bbsService.regBbsMainInfo(bbsMainDto);
            } else if ("U".equals(mode)) {
                if (!userInfo.getUsername().equals(bbsMainDto.getRegUserId())) {
                    result.put("resultCd", "FAIL");
                    result.put("msg", "글 수정 권한이 없습니다.");
                    return ResponseEntity.ok(result);
                }
                bbsService.modBbsMainInfo(bbsMainDto);
            } else {
                if (!userInfo.getUsername().equals(bbsMainDto.getRegUserId())) {
                    result.put("resultCd", "FAIL");
                    result.put("msg", "글 삭제 권한이 없습니다.");
                    return ResponseEntity.ok(result);
                }
                bbsService.delBbsMainInfo(bbsMainDto);
            }
        } catch (Exception e) {

            log.error("", e);
        }
        result.put("resultCd", "SUCCESS");
        return ResponseEntity.ok(result);
    }

}
