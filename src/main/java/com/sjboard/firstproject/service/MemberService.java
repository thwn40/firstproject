package com.sjboard.firstproject.service;

import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.dto.MemberJoinDto;
import com.sjboard.firstproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;

    @Transactional
    public Long Join(Member member){
        Member byLoginId = memberRepository.findByLoginId(member.getLoginId());
        if(byLoginId==null){
            memberRepository.save(member);
            return member.getId();
        }
        else{
            throw new IllegalStateException("이미 존재 하는 회원");
        }
    }



    @Transactional
    public Member Login(String loginId, String password){
        Member member = memberRepository.findByLoginId(loginId);

        if(member.getPassword().equals(password)){
            return member;
        }

        return null;

    }





}
