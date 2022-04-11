package com.sjboard.firstproject.controller;

import com.sjboard.firstproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    // 아이디 체크
    @PostMapping("/join/nameCheck")
    @ResponseBody
    public int nameCheck(@RequestParam("name") String name){
        log.info("userIdCheck 진입");
        log.info("전달받은 id : {}",name);
        int cnt = memberService.NameCheck(name);
        log.info("cnt : {}",cnt);
        return cnt;
    }

}
