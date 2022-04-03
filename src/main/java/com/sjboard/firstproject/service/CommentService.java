package com.sjboard.firstproject.service;

import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Comment;
import com.sjboard.firstproject.dto.CommentUpdateDto;
import com.sjboard.firstproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    public List<Comment> findAllByBoardId(Long boardId){
        List<Comment> comments = commentRepository.findByBoardId(boardId);
        return comments;
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
