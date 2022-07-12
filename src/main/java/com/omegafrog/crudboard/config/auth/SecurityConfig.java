package com.omegafrog.crudboard.config.auth;


import com.omegafrog.crudboard.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOauth2UserService customOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console옵션 사용을 위해서 해제
                .and()
                .authorizeRequests() // url별 권한 관리 설정 시작
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**","/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated() // 설정 이외는 인증된 사용자만 사용하게 함.
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOauth2UserService); // customOauth2userService : 소셜 로그인 정보를 받은 이후 내가 해줄 설정정의
    }
}
