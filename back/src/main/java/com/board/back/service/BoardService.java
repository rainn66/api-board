package com.board.back.service;

import com.board.back.dto.BoardDto;
import com.board.back.form.BoardFileForm;
import com.board.back.form.condition.BoardSearchCondition;
import com.board.back.form.validation.BoardDeleteForm;
import com.board.back.form.validation.BoardSaveForm;
import com.board.back.form.validation.BoardUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {

    /**
     * 게시글 목록
     */
    Page<BoardDto> getBoardList(Pageable pageable, BoardSearchCondition searchCondition, Long boardMainIdx) throws Exception;

    /**
     * 게시글 상세
     */
    BoardDto getBoardInfo(Long boardIdx) throws Exception;

    /**
     * 게시글 등록
     */
    void regBoardInfo(BoardSaveForm saveForm, List<BoardFileForm> fileForm) throws Exception;

    /**
     * 게시글 수정
     */
    void modBoardInfo(BoardUpdateForm updateForm, List<BoardFileForm> fileForm) throws Exception;

    /**
     * 게시글 삭제
     */
    void delBoardInfo(BoardDeleteForm deleteForm) throws Exception;

    /**
     * 게시글 업로드 파일 개별 삭제
     */
    void delBoardFileInfo(Long boardFileIdx) throws Exception;
}
