package com.sjboard.firstproject.dto;

import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberVo {

    private Long id;

    private String name;

    private String loginId;

    static public MemberVo from(Member member){
        return new MemberVo(member.getId(),
                member.getName(),
                member.getLoginId());
    }


}