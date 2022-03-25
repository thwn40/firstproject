package com.sjboard.firstproject.service;

import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

   @Autowired
   MemberRepository memberRepository;
   @Autowired
   MemberService memberService;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = Member.builder().loginId("thwn40").password("asdf123").build();


        //when
        Long saveId = memberService.Join(member);
        System.out.println("saveId = " + saveId);
        //then
        assertEquals(member, memberRepository.findById(saveId).get());

    }

    @Test
    public void 로그인() throws Exception{
    //given
        Member member = Member.builder().loginId("thwn40").password("asdf123").build();

    //when
        memberService.Join(member);
        Member login = memberService.Login("thwn40", "asdf123");

        //then

        assertEquals(member, memberRepository.findById(login.getId()).get());


    }

    @Test
    public void 로그인실패() throws Exception{
        //given
        Member member = Member.builder().loginId("thwn40").password("asdf123").build();

        //when
        memberService.Join(member);

        //then

        assertThrows(IllegalStateException.class, () -> {
            memberService.Login("thwn40", "asdsf123");
        });


    }

}