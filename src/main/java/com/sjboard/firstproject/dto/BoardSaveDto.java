package com.sjboard.firstproject.dto;

import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardSaveDto {

    private String title;

    private String content;

    private String author;

    private Member member;

    @Builder
    public BoardSaveDto(String title, String content, String author, Member member) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.member = member;

    }

    public Board toEntity(){
        return Board.builder().title(title).content(content).author(author).member(member).build();
    }
}
