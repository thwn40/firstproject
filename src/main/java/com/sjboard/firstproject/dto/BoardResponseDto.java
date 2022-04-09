package com.sjboard.firstproject.dto;

import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {
private Long id;
private String title;
private String content;
private String author;
private int view;
private int likeCount;
private LocalDateTime modifiedDate;


    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.view = entity.getView();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.likeCount= entity.getLikeCount().size();
        this.modifiedDate = entity.getModifiedDate();
    }
}
