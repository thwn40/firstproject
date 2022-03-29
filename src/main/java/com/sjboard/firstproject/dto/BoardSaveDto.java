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


    @Builder
    public BoardSaveDto(String title, String content) {
        this.title = title;
        this.content = content;

    }

    public Board toEntity(Member member){
        return Board.builder().member(member).author(member.getName()).title(title).content(content).build();
    }
}
