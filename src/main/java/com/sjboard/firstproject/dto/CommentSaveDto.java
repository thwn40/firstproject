package com.sjboard.firstproject.dto;

import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Comment;
import com.sjboard.firstproject.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CommentSaveDto {

    private Member member;

    private Board board;
    @NotBlank
    private String content;

    private Comment parentComment;


    @Builder
    public CommentSaveDto(Member member, Board board, String content, Comment parentComment) {
        this.member = member;
        this.board = board;
        this.content = content;
        this.parentComment = parentComment;
    }

    public Comment toEntity(){
        return Comment.builder().member(member).board(board).content(content).parent(parentComment).build();
    }
}
