package com.sjboard.firstproject.config.oauth;

import com.sjboard.firstproject.config.auth.MemberDetails;
import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.domain.Role;
import com.sjboard.firstproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MemberRepository memberRepository;

    //구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 구글 로그인 버튼 클릭 -> 구글 로그인 -> 로그인을 완료 -> code를 리턴 -> AccessToken요청 - > userRequest 정보 -> loadUser 호출 -> 구글로부터 회원프로필을 받아온다
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("sub");
        String loginId = provider + "_" + providerId;
        String password = bCryptPasswordEncoder.encode("asdf");
        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");
        Role role = Role.USER;

        Member member = memberRepository.findByLoginId(loginId).orElseGet(()->Member.builder()
                .name(name)
                .loginId(loginId)
                .role(Role.USER)
                .password(password)
                .provider(provider)
                .providerId(providerId)
                .build());
//        Member memberEntity = null;
//        if(member.isEmpty()){
//            memberEntity =Member.builder()
//                    .name(name)
//                    .loginId(loginId)
//                    .role(Role.USER)
//                    .password(password)
//                    .provider(provider)
//                    .providerId(providerId)
//                    .build();
//            memberRepository.save(memberEntity);
//        }else{
//            log.info("구글 로그인을 한적이 있음");
//        }

        memberRepository.save(member);
        log.info("memberEntity = {}",member);
        return new MemberDetails(member, oAuth2User.getAttributes());
    }
}
