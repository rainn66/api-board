package com.board.back.service.impl;

import com.board.back.dto.BoardDto;
import com.board.back.entity.Board;
import com.board.back.entity.BoardFile;
import com.board.back.entity.BoardMain;
import com.board.back.form.BoardFileForm;
import com.board.back.form.condition.BoardSearchCondition;
import com.board.back.form.validation.BoardDeleteForm;
import com.board.back.form.validation.BoardSaveForm;
import com.board.back.form.validation.BoardUpdateForm;
import com.board.back.repository.BoardFileRepository;
import com.board.back.repository.BoardMainRepository;
import com.board.back.repository.BoardRepository;
import com.board.back.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    private final BoardMainRepository boardMainRepository;

    private final BoardFileRepository boardFileRepository;

    @Value("${file.dir}")
    private String fileDir;

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
                findBoard.getBoardContent(), findBoard.getTopFixYn(), findBoard.getDelYn(), findBoard.getRegUserId(),
                findBoard.getRegDt(), findBoard.getBoardFiles());
    }

    /**
     * 게시글 등록
     */
    @Transactional
    public void regBoardInfo(BoardSaveForm saveForm, List<BoardFileForm> fileForm) {
        BoardMain findBoardMain = boardMainRepository.findByBoardMainIdx(saveForm.getBoardMainIdx());

        Board board = Board.builder()
                .boardMain(findBoardMain)
                .boardTitle(saveForm.getBoardTitle())
                .boardContent(saveForm.getBoardContent())
                .topFixYn(saveForm.getTopFixYn())
                .delYn("N")
                .build();
        Board savedBoard = boardRepository.save(board);

        if (fileForm != null && fileForm.size() > 0) {
            regBoardFileInfo(fileForm, savedBoard);
        }
    }

    /**
     * 게시글 수정
     */
    @Transactional //영속성컨텍스트 트랜잭션 내부에서만 동작(변경감지)
    public void modBoardInfo(BoardUpdateForm updateForm, List<BoardFileForm> fileForm) {
        Board findBoard = boardRepository.findByBoardIdx(updateForm.getBoardIdx());
        findBoard.update(boardMainRepository.findByBoardMainIdx(updateForm.getBoardMainIdx()),
                updateForm.getBoardTitle(), updateForm.getBoardContent(), updateForm.getTopFixYn());

        List<Long> boardFileIdxList = updateForm.getBoardFileIdxList();
        for (int i=0; i<fileForm.size(); i++) {
            BoardFileForm boardFileForm = fileForm.get(i);
            if (boardFileIdxList.get(i) == 0) {
                //신규등록은 Idx = 0 으로 넘어옴
                BoardFile boardFile = BoardFile.builder()
                        .board(findBoard)
                        .fileOrgNm(fileForm.get(i).getFileOrgNm())
                        .fileSaveNm(fileForm.get(i).getFileSaveNm())
                        .fileSavePath(fileForm.get(i).getFileSavePath())
                        .build();
                boardFileRepository.save(boardFile);
            } else {
                //기존파일 정보 업데이트
                Optional<BoardFile> boardFile = boardFileRepository.findById(boardFileIdxList.get(i));
                boardFile.ifPresent(bf -> bf.update(boardFileForm.getFileOrgNm(),
                        boardFileForm.getFileSaveNm(),
                        boardFileForm.getFileSavePath()));
            }
        }
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void delBoardInfo(BoardDeleteForm deleteForm) {
        Board findBoard = boardRepository.findByBoardIdx(deleteForm.getBoardIdx());
        findBoard.delete("Y"); //update 동작(변경감지)
    }

    /**
     * 게시판 업로드 파일 등록
     */
    private void regBoardFileInfo(List<BoardFileForm> fileForm, Board savedBoard) {
        fileForm.forEach(file -> {
            BoardFile boardFile = BoardFile.builder()
                    .board(savedBoard)
                    .fileOrgNm(file.getFileOrgNm())
                    .fileSaveNm(file.getFileSaveNm())
                    .fileSavePath(file.getFileSavePath())
                    .build();
            boardFileRepository.save(boardFile);
        });
    }
}
