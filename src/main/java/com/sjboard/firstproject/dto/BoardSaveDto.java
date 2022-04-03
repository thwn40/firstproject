package com.sjboard.firstproject.dto;

import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class BoardSaveDto {

    @NotBlank
    private String title;

    @NotBlank
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
