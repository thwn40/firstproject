package com.sjboard.firstproject.dto;

import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Comment;
import com.sjboard.firstproject.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentSaveDto {
    private Member member;
    private Board board;
    private String content;

    @Builder
    public CommentSaveDto(Member member, Board board, String content) {
        this.member = member;
        this.board = board;
        this.content = content;
    }

    public Comment toEntity(){
        return Comment.builder().member(member).board(board).content(content).build();

    }
}
