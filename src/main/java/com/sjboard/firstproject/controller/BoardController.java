package com.sjboard.firstproject.controller;

import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.dto.BoardResponseDto;
import com.sjboard.firstproject.dto.BoardSaveDto;
import com.sjboard.firstproject.repository.BoardRepository;
import com.sjboard.firstproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

private final BoardService boardService;

@GetMapping("/board/write")
    public String boardWriteForm() {
    return "boardSaveForm";
}

@PostMapping("/board/write")
    public String boardWrite(String title, String content){
//    Board board = Board.builder().title(boardSaveDto.getTitle()).member(boardSaveDto.getMember()).content(boardSaveDto.getContent()).build();
    boardService.save(BoardSaveDto.builder().title(title).content(content).build());
    return "redirect:/";
}

@GetMapping("/board/{id}")
    public String boardView(@PathVariable Long id,Model model){
    BoardResponseDto board = boardService.findById(id);
    boardService.updateView(id);
    model.addAttribute("board", board);
    return "boardView";

}

}
