package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScoreRepositoryImplJDBCTest {

    @Autowired
    ScoreRepositoryImpl repository;

    @Test
    void saveJDBCTest() {
        ScoreRequestDTO dto = new ScoreRequestDTO();
        dto.setName("나맹구");
        dto.setKor(80);
        dto.setEng(90);
        dto.setMath(95);
        Score score = new Score(dto);

        boolean result = repository.save(score);
        if (result == true) System.out.println("저장 성공");
        else System.out.println("실패");
    }

    @Test
    void findALLJDBCTest() {
        List<Score> scoreList = repository.findAll();
        for (Score s : scoreList) {
            System.out.println("s = " + s);
        }
    }

    @Test
    void findByStuNumJDBCTest() {
        Score score = repository.findByStuNum(2);
        System.out.println("score = " + score);
    }

    @Test
    void deleteByStuNumJDBCTest() {
        boolean b = repository.deleteByStuNum(2);
        if (b == true) System.out.println("삭제 성공");
        else System.out.println("삭제 실패");
    }
}
