package com.sjboard.firstproject.service;


import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Comment;
import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.dto.BoardResponseDto;
import com.sjboard.firstproject.dto.BoardSaveDto;
import com.sjboard.firstproject.dto.BoardUpdateDto;
import com.sjboard.firstproject.dto.CommentSaveDto;
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

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    //게시글 저장
    @Transactional
    public Long save(BoardSaveDto boardSaveDto, Member member) {

        return boardRepository.save(boardSaveDto.toEntity(member)).getId();
    }

    //게시글 수정
    @Transactional
    public Long update(Long id, BoardUpdateDto boardUpdateDto) {

        Optional<Board> byId = boardRepository.findById(id);
        if (byId.isEmpty()) {
            throw new IllegalStateException("게시글이 없습니다");
        } else {
            byId.get().update(boardUpdateDto.getTitle(), boardUpdateDto.getContent());
            return id;
        }
    }
    @Transactional(readOnly = true)
    public Page<Board> findAllDesc(Pageable pageable) {
        return boardRepository.findAll(pageable);
//
    }


    //게시글 조회
    public BoardResponseDto findById(Long id) {
        Optional<Board> byId = boardRepository.findById(id);

        if (byId.isEmpty()) {
            throw new IllegalStateException("게시글이 없습니다");
        } else {
            return new BoardResponseDto(byId.get());
        }
    }

    public Page<Board> findByTitleContainingOrContentContaining(String title, String content,Pageable pageable){
        Page<Board> byTitleContainingOrContentContaining = boardRepository.findByTitleContainingOrContentContaining(title, content, pageable);
        return byTitleContainingOrContentContaining;
    }

    //게시글 삭제
    @Transactional
    public void deleteById(Long id) {
        Optional<Board> byId = boardRepository.findById(id);

        if (byId.isEmpty()) {
            throw new IllegalStateException("게시글이 없습니다");
        } else {
            boardRepository.delete(byId.get());
        }
    }

    // 부모 댓글 저장
    @Transactional
    public Long commentParentSave(String content, Long memberId, Long boardId){

        Member member = memberRepository.findById(memberId).orElseThrow(()->{return new IllegalArgumentException("회원이 없습니다");});

        Board board = boardRepository.findById(boardId).orElseThrow(()->{return new IllegalArgumentException("게시글이 없습니다");});

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

        parentComment.addChildren(childComment);


            log.info("자식 댓글의 갯수 = {}", parentComment.getChildren().size());


        return commentRepository.save(childComment).getId();

    }

    @Transactional
    public int hit(Long id,boolean duplicate) {
        if(duplicate){
            return boardRepository.minusView(id);
        }
        return boardRepository.updateView(id); }

}

