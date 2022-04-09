package com.sjboard.firstproject.dto;

import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class LikesRequestDto {
    private Long memberId;
    private Long boardId;

    @Builder
    public LikesRequestDto(Long memberId, Long boardId) {
        this.memberId = memberId;
        this.boardId = boardId;
    }
}
