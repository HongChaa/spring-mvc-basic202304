package com.spring.mvc.chap05.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BoardResponseDTO {

    private String title; // 제목
    private String trimContent; // 내용
    private int viewCount; // 조회수
    private LocalDateTime trimRegDateTime; // 작성일자시간


    public void sliceContent(String originalContent) {
        if(originalContent.length() > 30) {
            this.trimContent = originalContent.substring(30);
        } else {
            this.trimContent = originalContent;
        }
    }

    public void sliceDateTime(LocalDateTime originalDateTime) {

    }


    public static BoardResponseDTO getDTOList() {
        BoardResponseDTO boardResponseDTO = new BoardResponseDTO();

        return boardResponseDTO;
    }



}
