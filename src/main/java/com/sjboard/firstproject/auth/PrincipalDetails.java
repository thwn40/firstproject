package com.sjboard.firstproject.auth;


import com.sjboard.firstproject.domain.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//시큐리티가 /로그인 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
// 로그인이 진행이 완료가 되면 시큐리티 session을 만들어 준다. (Security ContextHolder)
// 오브젝트 =>Authentication 타입 객체
// Authentication 안에 member 정보
// user 오브젝트 타입 => UserDetails 타입 객체
// Security Session => Authentication => UserDetailsType
@Getter
public class PrincipalDetails implements UserDetails {

private Member member; //콤포지션

    public PrincipalDetails(Member member){
        this.member = member;
    }

    //해당 member의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        // 1년동안 회원이 로그인을 안하면 휴면계정으로
        return true;
    }
}
