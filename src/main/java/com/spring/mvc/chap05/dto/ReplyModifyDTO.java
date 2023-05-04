package com.spring.mvc.chap05.dto;

import com.spring.mvc.chap05.entity.Reply;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Setter @Getter
@ToString @NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ReplyModifyDTO {

    @NotNull
    private Long bno;   // 원본글 번호

    @NotNull
    private Long rno; // 댓글 번호

    @NotBlank
    private String text;    // 댓글 내용

    public Reply toEntity(/*String writer*/) {
        return Reply.builder()
                .replyNo(this.rno)
                .replyText(this.text)
                .boardNo(this.bno)
//                .replyWriter(writer)
                .build();
    }
}
