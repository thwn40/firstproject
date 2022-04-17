package com.sjboard.firstproject.dto;


import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;


@Getter
@Setter
@NoArgsConstructor
public class MemberJoinDto {

    @NotEmpty(message ="아이디는 필수 입니다")
    @Email(message = "이메일 형식 이여야 합니다")
    private String loginId;

    @NotEmpty(message = "이름은 필수 입니다")
    private String name;

    @Size(min =8, message = "비밀번호는 최소 8자리 이상입니다")
    @NotBlank(message = "비밀번호는 필수 입니다")
    private String password;

    public MemberJoinDto(String loginId, String name, String password) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }

    public Member toEntity(){
        return Member.builder().name(name).loginId(loginId).password(password).role(Role.USER).build();
    }


}
