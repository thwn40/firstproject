package com.sjboard.firstproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberJoinDto {
    private String loginId;

    private String name;

    private String password;

    private String role;

}
