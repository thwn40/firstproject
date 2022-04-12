package com.sjboard.firstproject.Validator;

import com.sjboard.firstproject.dto.MemberJoinDto;
import com.sjboard.firstproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckLoginIdValidator extends AbstractValidator<MemberJoinDto> {
    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberJoinDto dto, Errors errors) {
        if (memberRepository.existsByLoginId(dto.toEntity().getLoginId())) {
            errors.rejectValue("loginId", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
        }


    }
}
