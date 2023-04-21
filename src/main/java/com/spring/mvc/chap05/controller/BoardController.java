package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.BoardResponseDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model) {
        List<BoardResponseDTO> boardList = boardService.getList();

        model.addAttribute("bList", boardList);
        return "chap05/list";
    }

    @GetMapping("/detail")
    public String detail(int boardNo) {

        return "chap05/detail";
    }

}
