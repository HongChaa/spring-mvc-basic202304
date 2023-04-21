package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.BoardResponseDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardResponseDTO> getList() {
         List<Board> boardList = boardRepository.findAll();
        List<BoardResponseDTO> boardResponseDTOList = BoardResponseDTO.getDTOList();
        return boardResponseDTOList;
    }

    // 중간처리 기능 자유롭게 사용
}
