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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    //게시글 저장
    @Transactional
    public Long save(BoardSaveDto boardSaveDto) {


        return boardRepository.save(boardSaveDto.toEntity()).getId();
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

    @Transactional
    public Long commentSave(String content, Long memberId, Long boardId){
        Member member = memberRepository.findById(memberId).orElseThrow(()->{return new IllegalArgumentException("회원이 없습니다");});
        Board board = boardRepository.findById(boardId).orElseThrow(()->{return new IllegalArgumentException("게시글이 없습니다");});

        Comment comment = CommentSaveDto.builder().board(board).content(content).member(member).build().toEntity();
        return commentRepository.save(comment).getId();


    }

    @Transactional
    public int updateView(Long id) { return boardRepository.updateView(id); }

}

