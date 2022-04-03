package com.sjboard.firstproject;

import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.dto.MemberJoinDto;
import com.sjboard.firstproject.repository.MemberRepository;
import com.sjboard.firstproject.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
public class FirstprojectApplication {
//
//	@Autowired
//	private static  MemberRepository memberRepository;
//	@Autowired
//	private  static MemberService memberService;
//	@Autowired
//	private static MemberJoinDto memberJoinDto;


	public static void main(String[] args) {

		SpringApplication.run(FirstprojectApplication.class, args);


//		MemberJoinDto memberJoinDto = new MemberJoinDto();
//		memberJoinDto.setName("이성준");
//		memberJoinDto.setLoginId("thwn40@naver.com");
//		memberJoinDto.setPassword("Tjdwns3365!");
//
////		System.out.println("memberJoinDto = " + memberJoinDto);
//		memberService.Join(memberJoinDto);
//
//		memberService.Login("thwn40@naver.com","Tjdwns3365!");


	}

}
