package com.board.back.service.impl;

import com.board.back.dto.BoardDto;
import com.board.back.entity.Board;
import com.board.back.entity.BoardFile;
import com.board.back.entity.BoardMain;
import com.board.back.exception.Exception404;
import com.board.back.dto.BoardFileDto;
import com.board.back.dto.condition.BoardSearchConditionDto;
import com.board.back.dto.validation.BoardDeleteDto;
import com.board.back.dto.validation.BoardSaveDto;
import com.board.back.dto.validation.BoardUpdateDto;
import com.board.back.repository.BoardFileRepository;
import com.board.back.repository.BoardMainRepository;
import com.board.back.repository.BoardRepository;
import com.board.back.service.BoardService;
import com.board.back.util.FileUtil;
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

    private final FileUtil fileUtil;

    @Value("${file.dir}")
    private String fileDir;

    /**
     * 게시글 목록
     */
    public Page<BoardDto> getBoardList(Pageable pageable, BoardSearchConditionDto searchCondition, Long boardMainIdx) throws Exception {
        try {
            return boardRepository.search(searchCondition, pageable, boardMainIdx);
        } catch (Exception e) {
            log.error("", e);
            throw new Exception(e.getMessage());
        }
    }


    /**
     * 게시글 상세
     */
    public BoardDto getBoardInfo(Long boardIdx) throws Exception {
        Board findBoard = boardRepository.findByBoardIdx(boardIdx).orElseThrow(Exception404::new);
        try {
            return new BoardDto(findBoard.getBoardIdx(), findBoard.getBoardMain().getBoardMainIdx(), findBoard.getBoardTitle(),
                    findBoard.getBoardContent(), findBoard.getTopFixYn(), findBoard.getDelYn(), findBoard.getRegUserId(),
                    findBoard.getRegDt(), findBoard.getBoardFiles());
        } catch (Exception e) {
            log.error("", e);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 게시글 등록
     */
    @Transactional
    public void regBoardInfo(BoardSaveDto saveForm, List<BoardFileDto> fileForm) throws Exception {
        BoardMain findBoardMain = boardMainRepository.findByBoardMainIdx(saveForm.getBoardMainIdx()).orElseThrow(Exception404::new);
        try {

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
        } catch (Exception e) {
            log.error("", e);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 게시글 수정
     */
    @Transactional //영속성컨텍스트 트랜잭션 내부에서만 동작(변경감지)
    public void modBoardInfo(BoardUpdateDto updateForm, List<BoardFileDto> fileFormList) throws Exception {

        Board findBoard = boardRepository.findByBoardIdx(updateForm.getBoardIdx()).orElseThrow(Exception404::new);

        BoardMain findBoardMain = boardMainRepository.findByBoardMainIdx(updateForm.getBoardMainIdx()).orElseThrow(Exception404::new);

        try {
            findBoard.update(findBoardMain, updateForm.getBoardTitle(), updateForm.getBoardContent(), updateForm.getTopFixYn());
            //파일 정보 저장
            if (!fileFormList.isEmpty()) {
                List<Long> boardFileIdxList = updateForm.getBoardFileIdxList();
                for (int i = 0; i < fileFormList.size(); i++) {
                    BoardFileDto boardFileDto = fileFormList.get(i);
                    if (boardFileIdxList.get(i) == 0) {
                        //신규등록은 Idx = 0
                        regBoardFile(findBoard, fileFormList.get(i));
                    } else {
                        Optional<BoardFile> boardFile = boardFileRepository.findById(boardFileIdxList.get(i));
                        //기존 파일 삭제 및 정보 업데이트
                        boardFile.ifPresent(bf -> {
                            fileUtil.deleteFile(bf.getFileSavePath(), bf.getFileSaveNm());
                            bf.update(boardFileDto.getFileOrgNm(),
                                    boardFileDto.getFileSaveNm(),
                                    boardFileDto.getFileSavePath());
                        });
                    }
                }
            }
        } catch (Exception e) {
            log.error("", e);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void delBoardInfo(BoardDeleteDto deleteForm) throws Exception {
        try {
            Board findBoard = boardRepository.findByBoardIdx(deleteForm.getBoardIdx()).orElseThrow(Exception404::new);
            findBoard.delete("Y"); //update 동작(변경감지)

            //파일삭제
            deleteForm.getBoardFileIdxList().forEach(this::delBoardFileInfoMethod);
        } catch (Exception e) {
            log.error("", e);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 게시판 업로드 파일 개별 삭제
     */
    @Transactional
    public void delBoardFileInfo(Long boardFileIdx) throws Exception {
        try {
            delBoardFileInfoMethod(boardFileIdx);
        } catch (Exception e) {
            log.error("", e);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 게시판 업로드 파일 등록 List
     */
    private void regBoardFileInfo(List<BoardFileDto> fileFormList, Board savedBoard) {
        fileFormList.forEach(file -> {
            regBoardFile(savedBoard, file);
        });
    }

    /**
     * 게시판 업로드 파일 등록
     */
    private void regBoardFile(Board savedBoard, BoardFileDto fileFrom) {
        BoardFile boardFile = BoardFile.builder()
                .board(savedBoard)
                .fileOrgNm(fileFrom.getFileOrgNm())
                .fileSaveNm(fileFrom.getFileSaveNm())
                .fileSavePath(fileFrom.getFileSavePath())
                .build();
        boardFileRepository.save(boardFile);
    }

    /**
     * 게시판 업로드 파일 개별 삭제(private)
     */
    public void delBoardFileInfoMethod(Long boardFileIdx) {
        Optional<BoardFile> boardFile = boardFileRepository.findById(boardFileIdx);
        boardFile.ifPresent(bf -> fileUtil.deleteFile(bf.getFileSavePath(), bf.getFileSaveNm()));
        boardFileRepository.deleteById(boardFileIdx);
    }
}
