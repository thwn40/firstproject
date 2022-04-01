package com.sjboard.firstproject.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class MemberJoinDto {

    @NotEmpty(message ="아이디는 필수 입니다")
    private String loginId;

    @NotEmpty(message = "이름은 필수에요")
    private String name;

    @NotBlank
    private String password;

}
