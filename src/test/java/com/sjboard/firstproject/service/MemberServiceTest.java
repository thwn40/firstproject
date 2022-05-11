//package com.sjboard.firstproject.service;
//
//
//import com.sjboard.firstproject.domain.Role;
//import com.sjboard.firstproject.dto.MemberJoinDto;
//import com.sjboard.firstproject.repository.BoardRepository;
//import com.sjboard.firstproject.repository.CommentRepository;
//import com.sjboard.firstproject.repository.MemberRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.After;
//import org.junit.Test;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.transaction.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class MemberServiceTest {
//
//    //member 필드
//    private static final String loginId = "test@test.com";
//    private static final String name = "테스트";
//    private static final Role role = Role.USER;
//    Long id;
//    @Autowired
//    MemberService memberService;
//    @Autowired
//    MemberRepository memberRepository;
//    @Autowired
//    BoardRepository boardRepository;
//    @Autowired
//    CommentRepository commentRepository;
//
//    @After
//    public void down(){
//        memberRepository.deleteById(id);
//    }
//
//    @Test
//    public void 회원가입(){
//        //given
//        MemberJoinDto memberJoinDto = new MemberJoinDto(loginId,name,"asdf");
//        //when
//        id = memberService.Join(memberJoinDto);
//        //then
//        assertThat(memberService.findMembers().get(0).getLoginId()).isEqualTo(loginId);
//    }
//
//    @Test
//    public void 중복_회원가입(){
//        MemberJoinDto memberJoinDto = new MemberJoinDto(loginId,name,"asdf");
//        id =memberService.Join(memberJoinDto);
//        MemberJoinDto memberJoinDto2 = new MemberJoinDto(loginId,name,"asdf");
//
//
//        assertThrows(IllegalStateException.class, ()->memberService.Join(memberJoinDto2));
//    }
//
//    @Test
//    public void 닉네임_변경(){
//        MemberJoinDto memberJoinDto = new MemberJoinDto(loginId,name,"asdf");
//        id = memberService.Join(memberJoinDto);
//
//        memberService.ChangeName(id,"change","asdf");
//
//        assertThat(memberService.findOne(id).getName()).isEqualTo("change");
//
//    }
//
//    @Test
//    public void 중복된_이름으로_닉네임_변경(){
//        MemberJoinDto memberJoinDto = new MemberJoinDto(loginId,name,"asdf");
//        id = memberService.Join(memberJoinDto);
//
//
//        assertThrows(IllegalArgumentException.class,()->memberService.ChangeName(id,name,"asdf"));
//
//    }
//
//
//
//
//
//}
