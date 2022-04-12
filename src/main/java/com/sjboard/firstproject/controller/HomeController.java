package com.sjboard.firstproject.controller;

import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.dto.BoardResponseDto;
import com.sjboard.firstproject.dto.BoardSearchRequestDTO;
import com.sjboard.firstproject.dto.MemberJoinDto;
import com.sjboard.firstproject.repository.BoardRepository;
import com.sjboard.firstproject.service.BoardService;
import com.sjboard.firstproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final MemberService memberService;
    private final BoardService boardService;

    @GetMapping(value = {"/","/board"})
//    @RequestParam(required = false, defaultValue = "")String searchText
    public String Home(Model model, @PageableDefault(page = 0, size=5, sort="createdDate", direction = Sort.Direction.DESC) Pageable pageable, BoardSearchRequestDTO boardSearchRequestDTO) {
//        Page<Board> board = boardService.findAllDesc(pageable);
        log.info("getType={}",boardSearchRequestDTO.getType());
        Page<Board> board = boardService.findByTitleContainingOrContentContaining(boardSearchRequestDTO,pageable);
        model.addAttribute("board", board);
//        model.addAttribute("boardSearchRequestDTO", BoardSearchRequestDTO.builder().build());


        return "home";
    }

    @GetMapping("/user")
    public @ResponseBody String user(){
        return "user";
    }
    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }
    @GetMapping("/manager")
    public @ResponseBody String manager(){
        return "manager";
    }

}
