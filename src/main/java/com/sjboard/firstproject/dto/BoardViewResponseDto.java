package com.sjboard.firstproject.dto;

import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Comment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BoardViewResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private int likeCount;
    List<Comment> comments;




    public BoardViewResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.likeCount= entity.getLikeCount().size();
        this.comments=entity.getComments();
    }
}
