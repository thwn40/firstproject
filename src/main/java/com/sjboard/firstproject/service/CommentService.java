package com.sjboard.firstproject.service;

import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Comment;
import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.dto.CommentSaveDto;
import com.sjboard.firstproject.dto.CommentUpdateDto;
import com.sjboard.firstproject.repository.BoardRepository;
import com.sjboard.firstproject.repository.CommentRepository;
import com.sjboard.firstproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final NoticeService noticeService;

    @Transactional
    public Long commentParentSave(String content, Long memberId, Long boardId){

        Member member = memberRepository.findById(memberId).orElseThrow(()->{return new IllegalArgumentException("회원이 없습니다");});

        Board board = boardRepository.findById(boardId).orElseThrow(()->{return new IllegalArgumentException("게시글이 없습니다");});

        if(member!=board.getMember()) {
            noticeService.makeNotice(member, board.getMember(), "내 게시글에 " + member.getName() + "님이 댓글을 달았습니다!");
        }


        Comment comment = CommentSaveDto.builder().board(board).content(content).member(member).build().toEntity();

        return commentRepository.save(comment).getId();
    }

    //자식 댓글 저장
    @Transactional
    public Long commentChildrenSave(String content, Long memberId, Long boardId,Long parentId){

        Member member = memberRepository.findById(memberId).orElseThrow(()->{return new IllegalArgumentException("회원이 없습니다");});

        Board board = boardRepository.findById(boardId).orElseThrow(()->{return new IllegalArgumentException("게시글이 없습니다");});

        Comment parentComment = commentRepository.findById(parentId).orElseThrow(()->{return new IllegalArgumentException("부모 댓글이 없습니다");});

        Comment childComment = CommentSaveDto.builder().board(board).content(content).member(member).parentComment(parentComment).build().toEntity();

        if(member!=board.getMember()) {
            noticeService.makeNotice(member, board.getMember(), "내 게시글에 " + member.getName() + "님이 댓글을 달았습니다!");
        }


        parentComment.addChildren(childComment);


        log.info("자식 댓글의 갯수 = {}", parentComment.getChildren().size());


        return commentRepository.save(childComment).getId();

    }
    public List<Comment> findAllByBoardId(Long boardId){
        List<Comment> comments = commentRepository.findByBoardId(boardId);
        return comments;
    }

    public Comment findById(Long parentId){
        Optional<Comment> comment = commentRepository.findById(parentId);
        if(comment.isEmpty()){
            throw new IllegalArgumentException("댓글이 없습니다");
        }
        else{
            return comment.get();
        }

    }

    public Page<Comment> findAllByMember(Member member, Pageable pageable){
        return commentRepository.findByMember(member,pageable);

    }

    @Transactional
    public Long deleteById(Long commentId){

        Optional<Comment> byId = commentRepository.findById(commentId);
        if(byId.isEmpty()){
            throw new IllegalArgumentException("댓글이 없습니다");
        }
        else{
            commentRepository.deleteById(commentId);
        }
        return commentId;
    }

    @Transactional
    public Long update(Long id, CommentUpdateDto commentUpdateDto){
        Optional<Comment> byId = commentRepository.findById(id);
        if (byId.isEmpty()) {
            throw new IllegalStateException("댓글이 없습니다");
        } else {
            byId.get().update(commentUpdateDto.getContent());
            return id;
        }
    }



    }
