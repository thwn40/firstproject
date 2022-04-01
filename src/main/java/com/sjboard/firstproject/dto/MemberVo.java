package com.sjboard.firstproject.dto;

import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class MemberVo {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String loginId;

    static public MemberVo from(Member member){
        return new MemberVo(member.getId(),
                member.getName(),
                member.getLoginId());
    }


}