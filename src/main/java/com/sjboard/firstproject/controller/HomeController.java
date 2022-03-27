package com.sjboard.firstproject.controller;

import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.dto.BoardResponseDto;
import com.sjboard.firstproject.dto.MemberJoinDto;
import com.sjboard.firstproject.repository.BoardRepository;
import com.sjboard.firstproject.service.BoardService;
import com.sjboard.firstproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.print.Pageable;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberService memberService;
    private final BoardService boardService;

    @GetMapping(value = {"/","/board"})
    public String Home(Model model, Pageable pageable) {
        List<BoardResponseDto> board = boardService.findAllDesc();
        model.addAttribute("board", board);
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
    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }
    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(MemberJoinDto memberJoinDto){

        System.out.println("memberid = " + memberJoinDto.getLoginId());
        System.out.println("memberpassword = " + memberJoinDto.getPassword());
        memberService.Join(memberJoinDto);
        return "redirect:/loginForm";
    }

    @GetMapping("/joinProc")
    public @ResponseBody String joinProc(){
        return "회원가입 완료";
    }


}
