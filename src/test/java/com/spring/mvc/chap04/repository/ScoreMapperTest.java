package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScoreMapperTest {

    @Autowired
    ScoreMapper mapper;


    @Test
    void saveTest() {
        Score score = Score.builder()
                .name("김천재")
                .kor(60)
                .eng(60)
                .math(70)
                .build();

        boolean flag = mapper.save(score);

        assertTrue(flag);
    }
}