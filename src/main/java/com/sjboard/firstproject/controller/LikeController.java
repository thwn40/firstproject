package com.sjboard.firstproject.controller;

import com.sjboard.firstproject.auth.MemberDetails;
import com.sjboard.firstproject.domain.Comment;
import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.dto.BoardResponseDto;
import com.sjboard.firstproject.dto.BoardSaveDto;
import com.sjboard.firstproject.dto.CommentSaveDto;
import com.sjboard.firstproject.service.LikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LikeController {

    private final LikesService likesService;

    @ResponseBody
    @PostMapping("/board/{id}/like")
    public Long 좋아요증가(@PathVariable Long id, Model model, @AuthenticationPrincipal MemberDetails principal) {
        Member member = principal.getMember();

        return likesService.likeUp(id,member.getId());
    }


}
