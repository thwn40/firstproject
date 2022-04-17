package com.sjboard.firstproject.config;

import com.sjboard.firstproject.config.oauth.PrincipalOauth2UserService;
import com.sjboard.firstproject.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationFailureHandler customFailureHandler;
    private PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/board/write").authenticated()
                .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
                .anyRequest().permitAll() // 위에 세개말고 아무나 들어갈수있음
                .and()
                .formLogin()
                .loginPage("/login")//로그인이 안되어있으면 로그인페이지로 보낸다.
                .usernameParameter("loginId")
                .loginProcessingUrl("/login")// login 주소가 호출이 되면 시큐리티가 낚아 채서 대신 로그인을 진행한다.
                .failureHandler(customFailureHandler)
                .defaultSuccessUrl("/")
                .and()
                .oauth2Login()
                .loginPage("/login") /*
                구글 로그인이 완료된 뒤의 후처리가 필요함 1.코드받기(인증) 2.엑세스토큰(권한) 3.사용자프로필 정보를 가져오고 4-1.그 정보를 토대로 회원가입을 자동으로 진행
                4.2 추가정보 입력시켜서 회원가입
                Tip. 코드x, (액세스토큰+사용자프로필정보 o)
                */
                .userInfoEndpoint()
                .userService(principalOauth2UserService);
    }
}
