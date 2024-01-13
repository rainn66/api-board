package com.board.back.service;

import com.board.back.dto.BoardDto;
import com.board.back.entity.Board;
import com.board.back.entity.BoardMain;
import com.board.back.model.BoardSearchCondition;
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
    public Page<BoardDto> getBoardList(Pageable pageable, BoardSearchCondition searchCondition, String boardMainIdx) {
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
    public void regBoardInfo(BoardDto boardDto) {

        //임시 카테고리 고정값
        BoardMain findBoardMain = boardMainRepository.findByBoardMainIdx(1L);
        Board board = Board.builder()
                .boardMain(findBoardMain)
                .boardTitle(boardDto.getBoardTitle())
                .boardContent(boardDto.getBoardContent())
                .topFixYn(boardDto.getTopFixYn())
                .delYn(boardDto.getDelYn())
                .build();
        boardRepository.save(board);
    }

    /**
     * 게시글 수정
     */
    public void modBoardInfo(BoardDto boardDto) {
        Board findBoard = boardRepository.findByBoardIdx(boardDto.getBoardIdx());
        findBoard.update(boardDto.getBoardTitle(), boardDto.getBoardContent(), boardDto.getTopFixYn(), boardDto.getDelYn());
    }


    /**
     * 게시글 삭제
     */
    public void delBoardInfo(BoardDto boardDto) {
        Board findBoard = boardRepository.findByBoardIdx(boardDto.getBoardIdx());
        findBoard.delete(boardDto.getDelYn());
    }
}
