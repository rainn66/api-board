package com.board.back.service;

import com.board.back.dto.BoardDto;
import com.board.back.dto.BoardFileDto;
import com.board.back.dto.condition.BoardSearchConditionDto;
import com.board.back.dto.validation.BoardDeleteDto;
import com.board.back.dto.validation.BoardSaveDto;
import com.board.back.dto.validation.BoardUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {

    /**
     * 게시글 목록
     */
    Page<BoardDto> getBoardList(Pageable pageable, BoardSearchConditionDto searchCondition, Long boardMainIdx) throws Exception;

    /**
     * 게시글 상세
     */
    BoardDto getBoardInfo(Long boardIdx) throws Exception;

    /**
     * 게시글 등록
     */
    void regBoardInfo(BoardSaveDto saveForm, List<BoardFileDto> fileForm) throws Exception;

    /**
     * 게시글 수정
     */
    void modBoardInfo(BoardUpdateDto updateForm, List<BoardFileDto> fileForm) throws Exception;

    /**
     * 게시글 삭제
     */
    void delBoardInfo(BoardDeleteDto deleteForm) throws Exception;

    /**
     * 게시글 업로드 파일 개별 삭제
     */
    void delBoardFileInfo(Long boardFileIdx) throws Exception;
}
