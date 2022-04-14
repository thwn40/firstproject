package com.sjboard.firstproject.controller;


import com.sjboard.firstproject.auth.MemberDetails;
import com.sjboard.firstproject.domain.Comment;
import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.domain.UploadFile;
import com.sjboard.firstproject.dto.*;
import com.sjboard.firstproject.repository.FileStore;
import com.sjboard.firstproject.service.AwsS3Service;
import com.sjboard.firstproject.service.BoardService;
import com.sjboard.firstproject.service.CommentService;
import com.sjboard.firstproject.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {
    private final CommentService commentService;

    private final BoardService boardService;

    private final AwsS3Service awsS3Service;



    //게시글 작성폼
    @GetMapping("/board/write")
    public String boardWriteForm(Model model) {
        model.addAttribute("boardSaveDto", new BoardSaveDto());
        return "boardSaveForm";
    }

    //게시글 작성


    @PostMapping("/board/write")
    public String boardWrite(@AuthenticationPrincipal MemberDetails principal, @Valid BoardSaveDto boardSaveDto, BindingResult bindingResult, Model model) throws IOException {

//        System.out.println("boardSaveDto.getContent() = " + boardSaveDto.getContent());
//
//        System.out.println("boardSaveDto.getTitle() = " + boardSaveDto.getTitle());
        if (bindingResult.hasErrors()) {
            return "boardSaveForm";
        }
        System.out.println("principal = " + principal.getMember().getName());
        boardService.save(boardSaveDto, principal.getMember());
        return "redirect:/board";
    }

    @Value("${file.dir}")
    private String fileDir;



    @PostMapping("/board/uploadImage")
    public @ResponseBody String saveImage(@ModelAttribute UploadImageDto uploadImageDto) throws IOException {
        log.info(String.valueOf(uploadImageDto.getImg().getOriginalFilename()));
        return awsS3Service.StoreImage(uploadImageDto.getImg());
        }




    //게시글 상세보기폼
    @GetMapping("/board/{id}")
    public String boardView(@PathVariable Long id, Model model,  @AuthenticationPrincipal MemberDetails principal) {
        BoardResponseDto board = boardService.findById(id);
        List<Comment> comments = commentService.findAllByBoardId(id);

        boardService.hit(id, false);

        if(principal==null){
           log.info("로그인이 안되있음");

        }
        else{
            log.info("로그인이 되있음");
            Member member = principal.getMember();
            model.addAttribute("member",member);
        }
        log.info("좋아요 수={}",board.getLikeCount());


        model.addAttribute("comments", comments);
        model.addAttribute("board", board);
        model.addAttribute("commentSaveDto", new CommentSaveDto());

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
    public Long commentSave(@PathVariable("id") Long boardId, @RequestParam(required = false) Long parentId, @Valid @RequestBody CommentSaveDto commentSaveDto, @AuthenticationPrincipal MemberDetails principal) {
//        MemberVo member = MemberVo.from(principal.getMember());
        Member member = principal.getMember();
        boardService.hit(boardId, true);
        log.info("{}", parentId);
        if (parentId == null) {
            return boardService.commentParentSave(commentSaveDto.getContent(), member.getId(), boardId);
        } else {
            return boardService.commentChildrenSave(commentSaveDto.getContent(), member.getId(), boardId, parentId);
        }


//        boardService.commentSave(CommentSaveDto.builder().content().board().member().build());

    }

    @PostMapping("board/comment/{id}/update")
    @ResponseBody
    public Long commentUpdate(@PathVariable("id") Long commentId, @Valid @RequestBody CommentUpdateDto commentUpdateDto) {

        return commentService.update(commentId, commentUpdateDto);

    }

    @ResponseBody
    @DeleteMapping("/board/comment/{id}")
    public Long commentDelete(@PathVariable("id") Long id) {
        return commentService.deleteById(id);
    }


}

