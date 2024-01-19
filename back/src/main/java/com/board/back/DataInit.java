package com.board.back;

import com.board.back.repository.BoardMainRepository;
import com.board.back.repository.BoardRepository;
import com.board.back.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final BoardRepository boardRepository;

    private final BoardMainRepository boardMainRepository;

    private final UserRepository userRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void init() {
        /*
        BoardMain boardMain1 = new BoardMain("공지사항", "Y");
        BoardMain boardMain2 = new BoardMain("자료실", "Y");
        BoardMain boardMain3 = new BoardMain("자유게시판", "Y");
        boardMainRepository.save(boardMain1);
        boardMainRepository.save(boardMain2);
        BoardMain savedBoardMain = boardMainRepository.save(boardMain3);

        BoardMain findBoardMain = boardMainRepository.findByBoardMainIdx(savedBoardMain.getBoardMainIdx());
        for (int i = 0; i < 24; i++) {
            Board board = Board.builder()
                    .boardMain(findBoardMain)
                    .boardTitle("게시판제목" + i)
                    .boardContent("게시판내용" + i)
                    .topFixYn("N")
                    .delYn("N")
                    .build();
            boardRepository.save(board);
        }

        String encPassword = passwordEncoder.encode("test!");
        Users userEntity = Users.builder()
                .userId("test")
                .userNm("테스트유저")
                .password(encPassword)
                .lastLoginDt(LocalDateTime.now())
                .build();

        userRepository.save(userEntity);*/
    }


}
