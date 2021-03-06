package com.sjboard.firstproject.controller;

import com.sjboard.firstproject.Validator.CheckLoginIdValidator;
import com.sjboard.firstproject.Validator.CheckNameValidator;
import com.sjboard.firstproject.config.auth.MemberDetails;
import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Comment;
import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.domain.Notice;
import com.sjboard.firstproject.dto.MemberJoinDto;
import com.sjboard.firstproject.dto.MemberUpdateDto;
import com.sjboard.firstproject.service.BoardService;
import com.sjboard.firstproject.service.CommentService;
import com.sjboard.firstproject.service.MemberService;
import com.sjboard.firstproject.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final CheckLoginIdValidator checkLoginIdValidator;
    private final CheckNameValidator checkNameValidator;
    private final MemberService memberService;
    private final BoardService boardService;
    private final CommentService commentService;
    private final NoticeService noticeService;
    private final AuthenticationManager authenticationManager;

    @InitBinder("MemberJoinDto")
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
    public String myPage(@AuthenticationPrincipal MemberDetails principal, Model model){


        log.info("my page ??????");
        Member member = principal.getMember();
        model.addAttribute("member", member);
        MemberUpdateDto memberUpdateDto = new MemberUpdateDto();
        memberUpdateDto.setLoginId(member.getLoginId());
        memberUpdateDto.setName(member.getName());
        memberUpdateDto.setCreatedDate(member.getCreatedDate());
        model.addAttribute("memberUpdateDto",memberUpdateDto);
        return "myPage";
    }

    //????????????
    @ResponseBody
    @PostMapping("/nameCheck")
    public int nameCheck(MemberUpdateDto dto) throws Exception{
        log.info("nameCheck??????");
        log.info("name = {}", dto.getName());
        int result = memberService.nameCheck(dto.getName());//??????????????? ????????? 1, ????????? 0

        return result;

    }

    @PostMapping("/myPage")
    public String changeProfile(@AuthenticationPrincipal MemberDetails principal, @Valid MemberUpdateDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "myPage";
        }
        log.info("????????? ??????????????? ??????");
        log.info("id={}",principal.getUsername());
        log.info("password={}",principal.getPassword());
        memberService.ChangeName(principal.getMember().getId(), dto.getName() ,dto.getPassword());

        return "redirect:/myPage";
    }

    @GetMapping("/myPage/Board")
    public String myPageBoard(Model model, @AuthenticationPrincipal MemberDetails principal,  @PageableDefault(page = 0, size=5, sort="createdDate", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Board> board = boardService.findAllByMember(principal.getMember(),pageable);
        model.addAttribute("boards", board);



        return "myPageBoard";
    }

    @GetMapping("/myPage/Comment")
    public String myPageComment(Model model, @AuthenticationPrincipal MemberDetails principal,  @PageableDefault(page = 0, size=5, sort="createdDate", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Comment> comment = commentService.findAllByMember(principal.getMember(),pageable);
        model.addAttribute("comments", comment);

        return "myPageComment";
    }

    @GetMapping("/myPage/Notice")
    public String myPageNotice(Model model, @AuthenticationPrincipal MemberDetails principal,  @PageableDefault(page = 0, size=5, sort="createdDate", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Notice> notice = noticeService.findNotice(principal.getMember(),pageable);
        model.addAttribute("notices", notice);

        return "myPageNotice";
    }

    @PostMapping("/myPage/Notice")
    public String myPageNoticeAllRead(Model model, @AuthenticationPrincipal MemberDetails principal){
        log.info("myPageNoticeAllRead ??????");
        noticeService.checkNotice(principal.getMember());
        return "redirect:/myPage/Notice";
    }





}
