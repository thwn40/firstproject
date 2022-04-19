package com.sjboard.firstproject.Validator;

import com.sjboard.firstproject.dto.MemberJoinDto;
import com.sjboard.firstproject.dto.MemberUpdateDto;
import com.sjboard.firstproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckNameValidator extends AbstractValidator<MemberJoinDto> {

    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberJoinDto dto, Errors errors) {
        if (memberRepository.existsByName(dto.toEntity().getName())) {
            errors.rejectValue("name", "이름 중복 오류", "이미 사용중인 이름 입니다.");
        }

    }

}
