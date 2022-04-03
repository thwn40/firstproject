package com.sjboard.firstproject.controller;

import com.sjboard.firstproject.auth.MemberDetails;
import com.sjboard.firstproject.domain.Comment;
import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.dto.*;
import com.sjboard.firstproject.service.BoardService;
import com.sjboard.firstproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final CommentService commentService;

    private final BoardService boardService;


    //게시글 작성폼
    @GetMapping("/board/write")
    public String boardWriteForm(Model model) {
        model.addAttribute("boardSaveDto",new BoardSaveDto());
        return "boardSaveForm";
    }

    //게시글 작성

    @PostMapping("/board/write")
    public String boardWrite(@Valid BoardSaveDto boardSaveDto, @AuthenticationPrincipal MemberDetails principal, BindingResult bindingResult) {
        System.out.println("boardSaveDto.getContent() = " + boardSaveDto.getContent());
        System.out.println("boardSaveDto.getTitle() = " + boardSaveDto.getTitle());
        if(bindingResult.hasErrors()){
            return "redirect:/";
        }
        System.out.println("principal = " + principal.getMember().getName());
        boardService.save(boardSaveDto,principal.getMember());
        return "redirect:/board";
    }


    //게시글 상세보기폼
    @GetMapping("/board/{id}")
    public String boardView(@PathVariable Long id, Model model) {
        BoardResponseDto board = boardService.findById(id);
        List<Comment> comments = commentService.findAllByBoardId(id);
        boardService.updateView(id);
        model.addAttribute("comments",comments);
        model.addAttribute("board", board);

        return "boardView";
    }

    // 게시글 수정폼
    @GetMapping("/board/{id}/update")
    public String boardUpdateForm(@PathVariable Long id, Model model) {
        BoardResponseDto board = boardService.findById(id);
        model.addAttribute("board", board);
        //boardService.update(id, BoardUpdateDto.builder().title(title).content(content).build());
        return "boardUpdate";
    }

    //게시글 수정
    @PostMapping("/board/{id}/update")
    public String boardUpdate(@PathVariable Long id, String title, String content) {
        BoardUpdateDto board = BoardUpdateDto.builder().title(title).content(content).build();
        boardService.update(id, board);
        return "redirect:/board/{id}";
    }

    //게시글 삭제
    @DeleteMapping("/board/{id}")
    public String boardDelete(@PathVariable Long id) {
        boardService.deleteById(id);
        return "redirect:/board";
    }

    //댓글 등록
    @ResponseBody
    @PostMapping("/board/{id}/comment")
    public Long commentSave(@PathVariable("id") Long boardId, @Valid @RequestBody CommentSaveDto commentSaveDto, @AuthenticationPrincipal MemberDetails principal){
//        MemberVo member = MemberVo.from(principal.getMember());
        Member member = principal.getMember();


//        boardService.commentSave(CommentSaveDto.builder().content().board().member().build());
        return boardService.commentSave(commentSaveDto.getContent(),member.getId(),boardId);

    }

    @PostMapping("board/comment/{id}/update")
    @ResponseBody
    public Long commentUpdate(@PathVariable("id") Long commentId, @Valid @RequestBody CommentUpdateDto commentUpdateDto){

        return commentService.update(commentId,commentUpdateDto);

    }

    @ResponseBody
    @DeleteMapping("/board/comment/{id}")
    public Long commentDelete(@PathVariable("id") Long id) {
        return commentService.deleteById(id);
    }
}

