package com.board.back.service;

import com.board.back.dto.BoardDto;
import com.board.back.entity.Board;
import com.board.back.entity.BoardMain;
import com.board.back.form.validation.BoardDeleteForm;
import com.board.back.form.validation.BoardSaveForm;
import com.board.back.form.condition.BoardSearchCondition;
import com.board.back.form.validation.BoardUpdateForm;
import com.board.back.repository.BoardMainRepository;
import com.board.back.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;

    private final BoardMainRepository boardMainRepository;

    /**
     * 게시글 목록
     */
    public Page<BoardDto> getBoardList(Pageable pageable, BoardSearchCondition searchCondition, Long boardMainIdx) {
        return boardRepository.search(searchCondition, pageable, boardMainIdx);
    }


    /**
     * 게시글 상세
     */
    public BoardDto getBoardInfo(Long boardIdx) {
        Board findBoard = boardRepository.findByBoardIdx(boardIdx);
        return new BoardDto(findBoard.getBoardIdx(), findBoard.getBoardMain().getBoardMainIdx(), findBoard.getBoardTitle(),
                findBoard.getBoardContent(), findBoard.getTopFixYn(), findBoard.getRegUserId(), findBoard.getRegDt());
    }

    /**
     * 게시글 등록
     */
    @Transactional
    public void regBoardInfo(BoardSaveForm saveForm) {
        BoardMain findBoardMain = boardMainRepository.findByBoardMainIdx(saveForm.getBoardMainIdx());
        Board board = Board.builder()
                .boardMain(findBoardMain)
                .boardTitle(saveForm.getBoardTitle())
                .boardContent(saveForm.getBoardContent())
                .topFixYn(saveForm.getTopFixYn())
                .delYn("N")
                .build();
        boardRepository.save(board);
    }

    /**
     * 게시글 수정
     */
    @Transactional //영속성컨텍스트 트랜잭션 내부에서만 동작(변경감지)
    public void modBoardInfo(BoardUpdateForm updateForm) {
        Board findBoard = boardRepository.findByBoardIdx(updateForm.getBoardIdx());
        findBoard.update(boardMainRepository.findByBoardMainIdx(updateForm.getBoardMainIdx()),
                updateForm.getBoardTitle(), updateForm.getBoardContent(), updateForm.getTopFixYn());
    }


    /**
     * 게시글 삭제
     */
    @Transactional
    public void delBoardInfo(BoardDeleteForm deleteForm) {
        Board findBoard = boardRepository.findByBoardIdx(deleteForm.getBoardIdx());
        findBoard.delete("Y"); //update 동작(변경감지)
    }
}
