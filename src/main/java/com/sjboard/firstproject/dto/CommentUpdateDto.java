package com.sjboard.firstproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CommentUpdateDto {

    @NotBlank
private String content;

    @Builder
    public CommentUpdateDto(String content) {
        this.content = content;
    }
}
