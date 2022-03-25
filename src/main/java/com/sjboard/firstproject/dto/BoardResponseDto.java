package com.sjboard.firstproject.dto;

import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Member;
import lombok.Getter;

@Getter
public class BoardResponseDto {
private Long id;
private String title;
private String content;


    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
