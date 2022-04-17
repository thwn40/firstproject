package com.sjboard.firstproject.config.auth;

import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//시큐리티 설정에서 loginProcessingUrl("/login");
// /login 오청이 오면 자동으로 UserDetailsService 타입으로 ioc되어 있는 loadUserByUsername 함수가 실행
@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {


    private final MemberRepository memberRepository;


    // 시큐리티 session = (내부 Authentication(내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByLoginId(loginId);
        if(member.isPresent()){
            return new MemberDetails(member.get());
        }
        throw new UsernameNotFoundException(loginId);
    }
}
