package com.board.back.controller;

import com.board.back.entity.BoardMain;
import com.board.back.repository.BoardMainRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
public class MainController {

    private final BoardMainRepository boardMainRepository;

    @GetMapping("/side")
    public ResponseEntity<Map<String, Object>> getSideList() {
        Map<String, Object> result = new HashMap<String, Object>();

        //게시판 카테고리 전체 조회
        List<BoardMain> boardMainList = boardMainRepository.findAll();
        result.put("boardMainList", boardMainList);

        return ResponseEntity.ok(result);
    }
}
