package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Repository
public class BoardRepositoryImpl implements BoardRepository {

    private final static Map<Integer, Board> boardMap;

    private static int sequence;

    static {
        boardMap = new HashMap<>();
        Board board1 = new Board(++sequence, "룰루랄라", "독서왕이 될거야");
        Board board2 = new Board(++sequence, "룰루랄라2", "해적왕이 될거야");
        Board board3 = new Board(++sequence, "룰루랄라3", "요리왕이 될거야");

        boardMap.put(board1.getBoardNo(), board1);
        boardMap.put(board2.getBoardNo(), board2);
        boardMap.put(board3.getBoardNo(), board3);
    }
    @Override
    public List<Board> findAll() {
        return boardMap.values()
                .stream()
                .sorted(comparing(Board::getBoardNo))
                .collect(toList());

    }

    @Override
    public Board findOne(int boardNo) {
        return null;
    }

    @Override
    public boolean save(Board board) {
        return false;
    }

    @Override
    public boolean deleteByNo(int boardNo) {
        return false;
    }
}
