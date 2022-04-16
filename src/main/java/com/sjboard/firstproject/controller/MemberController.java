package com.sjboard.firstproject.controller;

import com.sjboard.firstproject.Validator.CheckLoginIdValidator;
import com.sjboard.firstproject.Validator.CheckNameValidator;
import com.sjboard.firstproject.auth.MemberDetails;
import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.dto.BoardSearchRequestDTO;
import com.sjboard.firstproject.dto.MemberJoinDto;
import com.sjboard.firstproject.service.BoardService;
import com.sjboard.firstproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final CheckLoginIdValidator checkLoginIdValidator;
    private final CheckNameValidator checkNameValidator;
    private final BoardService boardService;

    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkLoginIdValidator);
        binder.addValidators(checkNameValidator);
    }

    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "exception", required = false) String exception, Model model) {
        model.addAttribute("error", error);


        model.addAttribute("exception", exception);
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(Model model) {
        model.addAttribute("memberJoinDto", new MemberJoinDto());
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(@Valid MemberJoinDto memberJoinDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "joinForm";
        }


        memberService.Join(memberJoinDto);
        return "loginForm";


    }

    @GetMapping("/myPage")
    public String myPage(Model model, @AuthenticationPrincipal MemberDetails principal,  @PageableDefault(page = 0, size=5, sort="createdDate", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Board> board = boardService.findAllDescById(principal.getMember().getId(),pageable);
        model.addAttribute("board", board);
        return "myPage";
    }




}
