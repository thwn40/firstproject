package com.sjboard.firstproject.dto;

import lombok.Getter;

@Getter
public class MemberJoinDto {

    private Long id;
    private String loginId;

    public MemberJoinDto(Long id, String loginId) {
        this.id = id;
        this.loginId = loginId;
    }
}
