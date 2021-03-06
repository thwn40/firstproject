package com.sjboard.firstproject.service;

import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.domain.Role;
import com.sjboard.firstproject.dto.MemberJoinDto;
import com.sjboard.firstproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final AuthenticationManager authenticationManager;



    //회원가입

    @Transactional
    public Long Join(MemberJoinDto memberJoinDto){
        Optional<Member> byLoginId = memberRepository.findByLoginId(memberJoinDto.getLoginId());
        if(byLoginId.isEmpty()){
            memberJoinDto.setPassword(bCryptPasswordEncoder.encode(memberJoinDto.getPassword()));
            return  memberRepository.save(Member.builder()
                    .name(memberJoinDto.getName())
                    .loginId(memberJoinDto.getLoginId())
                    .password(memberJoinDto.getPassword())
                    .role(Role.USER)
                    .build()).getId();
        }
        else{
            throw new IllegalStateException("이미 존재 하는 회원");
        }
    }



    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.getById(memberId);
    }


    public int nameCheck(String name) {
        log.info("name check 진입");

        Optional<Member> byName = memberRepository.findByName(name);

        if (byName.isEmpty()) {
            return 0;
        }
        else{
            log.info("가져온 멤버 = {}",byName.get());
            return 1;
        }


    }


    public int loginIdCheck(String loginId) {
        if(loginId.length()==0){
            return 2;
        }
        Optional<Member> byLoginId = memberRepository.findByLoginId(loginId);

        if (byLoginId.isEmpty()) {

            return 0;
        } else {
            log.info("가져온 멤버 = {}", byLoginId.get());
            return 1;
        }


    }
    @Transactional
    public void ChangeName(Long id, String name, String password){
        Member member = memberRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("회원이 없습니다");
        });

        String rawPassword = password;
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        if(nameCheck(name)==1){
            throw new IllegalArgumentException("중복된 이름 입니다.");
        };
        member.update(name,encPassword);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(member.getLoginId(),rawPassword));
        SecurityContextHolder.getContext().setAuthentication(authentication);


    }

    }
