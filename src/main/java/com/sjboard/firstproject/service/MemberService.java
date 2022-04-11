package com.sjboard.firstproject.service;

import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.dto.MemberJoinDto;
import com.sjboard.firstproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {


    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Long Join(MemberJoinDto memberJoinDto){
        Member byLoginId = memberRepository.findByLoginId(memberJoinDto.getLoginId());
        if(byLoginId==null){
            String rawPassword = memberJoinDto.getPassword();
            return  memberRepository.save(Member.builder()
                    .name(memberJoinDto.getName())
                    .loginId(memberJoinDto.getLoginId())
                    .password(bCryptPasswordEncoder.encode(rawPassword))
                    .role("ROLE_USER")
                    .build()).getId();
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

        throw new IllegalStateException("아이디나 비밀번호가 틀립니다");

    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.getById(memberId);
    }


    public int NameCheck(String name) {
        Optional<Member> byName = memberRepository.findByName(name);

        if (byName.isEmpty()) {

            return 0;
        }
        else{
            log.info("가져온 멤버 = {}",byName.get());
            return 1;
        }

    }


}
