package com.sjboard.firstproject.dto;

import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.domain.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class MemberUpdateDto {




    @NotEmpty(message = "이름은 필수 입니다")
    private String name;

    @Size(min =8, message = "비밀번호는 최소 8자리 이상입니다")
    @NotBlank(message = "비밀번호는 필수 입니다")
    private String password;

    public MemberUpdateDto(String loginId, String name, String password) {
        this.name = name;
        this.password = password;
    }


}
