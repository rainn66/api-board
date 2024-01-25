package com.board.back.controller;

import com.board.back.dto.BoardDto;
import com.board.back.form.BoardFileForm;
import com.board.back.form.condition.BoardSearchCondition;
import com.board.back.form.validation.BoardDeleteForm;
import com.board.back.form.validation.BoardSaveForm;
import com.board.back.form.validation.BoardUpdateForm;
import com.board.back.repository.BoardMainRepository;
import com.board.back.service.BoardService;
import com.board.back.util.FileUtil;
import com.board.back.util.JwtUtil;
import com.board.back.util.TokenRequestFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    private final BoardMainRepository boardMainRepository;

    private final JwtUtil jwtUtil;

    private final TokenRequestFilter tokenRequestFilter;

    private final FileUtil fileUtil;

    /**
     * 목록 조회
     */
    @GetMapping
    public Page<BoardDto> boardList(Pageable pageable,
                                    BoardSearchCondition searchCondition,
                                    @RequestParam(value = "boardMainIdx", required = true) Long boardMainIdx) {
        return boardService.getBoardList(pageable, searchCondition, boardMainIdx);
    }

    /**
     * 등록 폼 조회(빈 화면, 게시판 카테고리 가져오기)
     */
    @GetMapping("/add")
    public ResponseEntity<Map<String, Object>> boardAddForm() {
        Map<String, Object> result = new HashMap<>();
        result.put("boardMainList", boardMainRepository.findAll());
        return ResponseEntity.ok(result);
    }

    /**
     * 등록
     */
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> boardAdd(@RequestPart("body") @Valid BoardSaveForm saveForm,
                                                        @RequestPart(value = "file", required = false) List<MultipartFile> file,
                                                        BindingResult bindingResult) throws IOException {
        Map<String, Object> result = new HashMap<>();

        if (bindingResult.hasErrors()) {
            result.put("resultCd", "FAIL");
            result.put("msg", "필수 값 오류");
        } else {
            List<BoardFileForm> fileForm = fileUtil.saveFiles(file, String.valueOf(saveForm.getBoardMainIdx()));
            boardService.regBoardInfo(saveForm, fileForm);

            result.put("resultCd", "SUCCESS");
            result.put("msg", "등록되었습니다.");
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 수정 폼 조회
     */
    @GetMapping("/edit/{boardIdx}")
    public ResponseEntity<Map<String, Object>> boardEditForm(@PathVariable Long boardIdx) {
        Map<String, Object> result = new HashMap<>();
        result.put("boardMainList", boardMainRepository.findAll());
        result.put("boardInfo", boardService.getBoardInfo(boardIdx));
        return ResponseEntity.ok(result);
    }

    /**
     * 수정
     */
    @PostMapping("/edit")
    public ResponseEntity<Map<String, Object>> boardEdit(@RequestPart(value = "body") @Validated BoardUpdateForm updateForm,
                                                         @RequestPart(value = "file", required = false) List<MultipartFile> file,
                                                         HttpServletRequest request,
                                                         BindingResult bindingResult) throws IOException {
        Map<String, Object> result = new HashMap<>();

        //Spring security 에 세팅된 회원명으로 비교 시 사용
        //String securityUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        //token 작성자 확인(작성자만 수정 가능하도록)
        String tokenUserId = decodeToken(request);
        if (checkUserAuth(updateForm.getRegUserId(), tokenUserId, result)) {
            return ResponseEntity.ofNullable(result);
        }

        if (bindingResult.hasErrors()) {
            result.put("resultCd", "FAIL");
            result.put("msg", "필수 값 오류");
        } else {
            List<BoardFileForm> fileForm = fileUtil.saveFiles(file, String.valueOf(updateForm.getBoardMainIdx()));
            boardService.modBoardInfo(updateForm, fileForm);
            result.put("resultCd", "SUCCESS");
            result.put("msg", "수정되었습니다.");
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 삭제
     */
    @PostMapping("/delete")
    public ResponseEntity<Map<String, Object>> boardDelete(@RequestBody BoardDeleteForm deleteForm, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();

        //token 작성자 확인(작성자만 삭제 가능하도록)
        String tokenUserId = decodeToken(request);
        if (checkUserAuth(deleteForm.getRegUserId(), tokenUserId, result)) {
            return ResponseEntity.ofNullable(result);
        }
        boardService.delBoardInfo(deleteForm);
        result.put("resultCd", "SUCCESS");
        result.put("msg", "삭제되었습니다.");
        return ResponseEntity.ok(result);
    }


    private String decodeToken(HttpServletRequest request) {
        return jwtUtil.getUserFromToken(tokenRequestFilter.parseJwt(request));
    }

    private boolean checkUserAuth(String userId, String tokenUserId, Map<String, Object> result) {
        //UserDetails.getUsername == userId
        if (!tokenUserId.equals(userId)) {
            result.put("resultCd", "FAIL");
            result.put("msg", "글 수정 권한이 없습니다.");
            return true;
        }
        return false;
    }

}
