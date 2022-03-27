package com.sjboard.firstproject.auth;

import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//시큐리티 설정에서 loginProcessingUrl("/login");
// /login 오청이 오면 자동으로 UserDetailsService 타입으로 ioc되어 있는 loadUserByUsername 함수가 실행
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {


    private final MemberRepository memberRepository;


    // 시큐리티 session = (내부 Authentication(내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member member = memberRepository.findByLoginId(loginId);
        if(member!= null){
            return new PrincipalDetails(member);
        }
        return null;
    }
}
