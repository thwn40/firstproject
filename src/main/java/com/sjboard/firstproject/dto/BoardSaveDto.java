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



    @Builder
    public BoardSaveDto(String title, String content) {
        this.title = title;
        this.content = content;

    }

    public Board toEntity(){
        return Board.builder().title(title).content(content).author("관리자").build();
    }
}
