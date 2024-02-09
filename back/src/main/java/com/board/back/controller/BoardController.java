package com.board.back.controller;

import com.board.back.entity.BoardFile;
import com.board.back.exception.Exception400;
import com.board.back.exception.Exception401;
import com.board.back.exception.Exception404;
import com.board.back.dto.BoardFileDto;
import com.board.back.dto.condition.BoardSearchConditionDto;
import com.board.back.dto.validation.BoardDeleteDto;
import com.board.back.dto.validation.BoardFileDeleteDto;
import com.board.back.dto.validation.BoardSaveDto;
import com.board.back.dto.validation.BoardUpdateDto;
import com.board.back.repository.BoardFileRepository;
import com.board.back.repository.BoardMainRepository;
import com.board.back.service.BoardService;
import com.board.back.util.FileUtil;
import com.board.back.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

    private final BoardFileRepository boardFileRepository;

    private final JwtUtil jwtUtil;

    private final FileUtil fileUtil;

    /**
     * 목록 조회
     */
    @GetMapping
    public ResponseEntity<?> boardList(Pageable pageable,
                                       BoardSearchConditionDto searchCondition,
                                       @RequestParam(value = "boardMainIdx", required = true) Long boardMainIdx) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("boardList", boardService.getBoardList(pageable, searchCondition, boardMainIdx));
        return ResponseEntity.ok(result);
    }

    /**
     * 등록 폼 조회(빈 화면, 게시판 카테고리 가져오기)
     */
    @GetMapping("/add")
    public ResponseEntity<?> boardAddForm() throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("boardMainList", boardMainRepository.findAll()); //게시판 카테고리 조회
        return ResponseEntity.ok(result);
    }

    /**
     * 등록
     */
    @PostMapping("/add")
    public ResponseEntity<?> boardAdd(@RequestPart("body") @Valid BoardSaveDto saveForm,
                                      @RequestPart(value = "file", required = false) List<MultipartFile> file,
                                      BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            FieldError error = bindingResult.getFieldErrors().get(0);
            throw new Exception400(error.getDefaultMessage());
        } else {
            List<BoardFileDto> fileForm = new ArrayList<>();
            if (file != null && !file.isEmpty()) {
                fileForm = fileUtil.saveFiles(file, String.valueOf(saveForm.getBoardMainIdx()));
            }
            boardService.regBoardInfo(saveForm, fileForm);
        }
        return ResponseEntity.ok(null);
    }

    /**
     * 수정 폼 조회
     */
    @GetMapping("/edit/{boardIdx}")
    public ResponseEntity<?> boardEditForm(@PathVariable Long boardIdx) {
        try {
            Map<String, Object> result = new HashMap<>();
            result.put("boardMainList", boardMainRepository.findAll());
            result.put("boardInfo", boardService.getBoardInfo(boardIdx));
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            //ControllerAdvice 500 error 로 던지도록 설정해둠(임시)
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 수정
     */
    @PostMapping("/edit")
    public ResponseEntity<?> boardEdit(@RequestPart(value = "body") @Valid BoardUpdateDto updateForm,
                                       @RequestPart(value = "file", required = false) List<MultipartFile> file,
                                       HttpServletRequest request,
                                       BindingResult bindingResult) throws Exception {
        //Spring security 에 세팅된 회원명으로 비교 시 사용
        //String securityUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        //token 작성자 확인(작성자만 수정 가능하도록)
        String jwt = request.getHeader("Authorization");
        String tokenUserId = JwtUtil.tokenToUserId(jwt);
        if (!updateForm.getRegUserId().equals(tokenUserId)) {
            throw new Exception401();
        }

        if (bindingResult.hasErrors()) {
            FieldError error = bindingResult.getFieldErrors().get(0);
            throw new Exception400(error.getDefaultMessage());
        } else {
            List<BoardFileDto> fileForm = new ArrayList<>();
            if (file != null && !file.isEmpty()) {
                fileForm = fileUtil.saveFiles(file, String.valueOf(updateForm.getBoardMainIdx()));
            }
            boardService.modBoardInfo(updateForm, fileForm);
        }
        return ResponseEntity.ok(null);
    }

    /**
     * 삭제
     */
    @PostMapping("/delete")
    public ResponseEntity<?> boardDelete(@RequestBody @Valid BoardDeleteDto deleteForm,
                                         HttpServletRequest request,
                                         BindingResult bindingResult) throws Exception {
        //token 작성자 확인(작성자만 삭제 가능하도록)
        String jwt = request.getHeader("Authorization");
        String tokenUserId = JwtUtil.tokenToUserId(jwt);
        if (!deleteForm.getRegUserId().equals(tokenUserId)) {
            throw new Exception401();
        }
        if (bindingResult.hasErrors()) {
            FieldError error = bindingResult.getFieldErrors().get(0);
            throw new Exception400(error.getDefaultMessage());
        } else {
            boardService.delBoardInfo(deleteForm);
        }
        return ResponseEntity.ok(null);
    }

    /**
     * 게시판 업로드 파일 개별 삭제
     */
    @PostMapping("/file/delete")
    public ResponseEntity<?> boardFileDelete(@RequestBody @Valid BoardFileDeleteDto boardFileDeleteDto,
                                             HttpServletRequest request,
                                             BindingResult bindingResult) throws Exception {
        //token 작성자 확인(작성자만 삭제 가능하도록)
        String jwt = request.getHeader("Authorization");
        String tokenUserId = JwtUtil.tokenToUserId(jwt);
        if (!boardFileDeleteDto.getRegUserId().equals(tokenUserId)) {
            throw new Exception401();
        }
        if (bindingResult.hasErrors()) {
            FieldError error = bindingResult.getFieldErrors().get(0);
            throw new Exception400(error.getDefaultMessage());
        } else {
            boardService.delBoardFileInfo(boardFileDeleteDto.getBoardFileIdx());
        }
        return ResponseEntity.ok(null);
    }

    /**
     * 게시판 상세 - 파일 다운로드
     */
    @PostMapping("/file/download")
    public ResponseEntity<?> boardFileDownload(@RequestBody @Valid BoardFileDeleteDto boardFileDownForm,
                                               HttpServletRequest request,
                                               BindingResult bindingResult) throws Exception {
        //token 작성자 확인(작성자만 삭제 가능하도록)
        String jwt = request.getHeader("Authorization");
        String tokenUserId = JwtUtil.tokenToUserId(jwt);
        if (!boardFileDownForm.getRegUserId().equals(tokenUserId)) {
            //responseType : Blob 이기 때문에 정상적으로 alert 출력이 안됨 -> 웹 콘솔에 Unauthorized 출력
            throw new Exception401();
        }
        if (bindingResult.hasErrors()) {
            FieldError error = bindingResult.getFieldErrors().get(0);
            throw new Exception400(error.getDefaultMessage());
        }

        BoardFile findFileInfo = boardFileRepository.findById(boardFileDownForm.getBoardFileIdx()).orElseThrow(Exception404::new);

        UrlResource resource = new UrlResource("file:" + findFileInfo.getFileSavePath() + "/" + findFileInfo.getFileSaveNm());

        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        log.info("contentType : {}", contentType);
        log.info("filePath : {}", findFileInfo.getFileSavePath() + "/" + findFileInfo.getFileSaveNm());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode(findFileInfo.getFileOrgNm(), StandardCharsets.UTF_8))
                .body(resource);
    }

}
