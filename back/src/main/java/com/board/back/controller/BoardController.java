package com.board.back.controller;

import com.board.back.dto.BoardDto;
import com.board.back.model.BoardSearchCondition;
import com.board.back.service.BoardService;
import com.board.back.service.UserService;
import com.board.back.util.JwtUtil;
import com.board.back.util.TokenRequestFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/board")
public class BoardController {
    private final UserService userService;
    private final BoardService boardService;
    private final JwtUtil jwtUtil;
    private final TokenRequestFilter tokenRequestFilter;

    @GetMapping
    public Page<BoardDto> boardList(Pageable pageable,
                                    BoardSearchCondition searchCondition,
                                    @RequestParam(value = "boardMainIdx", required = true) Long boardMainIdx) {
        return boardService.getBoardList(pageable, searchCondition, boardMainIdx);
    }

    @GetMapping("/{boardIdx}")
    public BoardDto boardInfo(@PathVariable Long boardIdx, HttpServletRequest request) {
        return boardService.getBoardInfo(boardIdx);
    }


    @PostMapping("/Exec")
    public ResponseEntity<Map<String, Object>> boardExec(@RequestParam String mode,
                                                         @RequestBody BoardDto boardDto,
                                                         HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();

        //token 작성자 확인
        String jwtToken = tokenRequestFilter.parseJwt(request);
        String userId = jwtUtil.getUserFromToken(jwtToken);
        UserDetails userInfo = userService.loadUserByUsername(userId);
        //검증

        //서비스
        if ("R".equals(mode)) {
            boardService.regBoardInfo(boardDto);
        } else if ("U".equals(mode)) {
            if (checkUserAuth(boardDto.getRegUserId(), result, userInfo)) {
                return ResponseEntity.ok(result);
            }
            boardService.modBoardInfo(boardDto);
        } else {
            if (checkUserAuth(boardDto.getRegUserId(), result, userInfo)) {
                return ResponseEntity.ok(result);
            }
            boardService.delBoardInfo(boardDto);
        }
        return ResponseEntity.ok(result);
    }

    private boolean checkUserAuth(String userId, Map<String, Object> result, UserDetails userInfo) {
        //UserDetails.getUsername == userId
        if (!userInfo.getUsername().equals(userId)) {
            result.put("resultCd", "FAIL");
            result.put("msg", "글 수정 권한이 없습니다.");
            return true;
        }
        return false;
    }

}
