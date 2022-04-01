package com.sjboard.firstproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class BoardUpdateDto {

    @NotBlank
private String title;
    @NotBlank
private String content;

    @Builder
    public BoardUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
