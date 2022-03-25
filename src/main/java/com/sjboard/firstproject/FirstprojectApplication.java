package com.sjboard.firstproject;

import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.repository.MemberRepository;
import com.sjboard.firstproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FirstprojectApplication {
	private static MemberRepository memberRepository;
	private static MemberService memberService;


	public static void main(String[] args) {

		SpringApplication.run(FirstprojectApplication.class, args);





	}

}
