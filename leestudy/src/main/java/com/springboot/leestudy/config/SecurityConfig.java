package com.springboot.leestudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity // 기존의 WebSecurityConfigurerAdapter의 설정을 비활성화하고, 현재 클래스(SecurityConfig)의 설정을 따르게함
@Configuration // 이 객체가 Configuration 관련 설정 객체, @Bean어노테이션을 쓸 수 있게됨
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean(name = "BCrypt")
	public BCryptPasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder(); // IoC에 등록 (굳이 Class를 따로 만들어 extends 하여 쓸 필요없음)
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // 로그인 시 입력란에 csrf토큰이 각각 있는데, 이 토큰을 먼저 검사, 이는 위조된 사이트나 postman같은 앱에서 요청시 응답을 방지
		http.authorizeRequests() // 인증관련 요청
		//.antMatchers("/index") // 해당 등록된 uri는 인증을 거치도록
		.antMatchers("/search/**") // 이 uri들은
		.authenticated() // 인증 거치기
		.anyRequest() // 나머지 요청들은
		.permitAll() // 인증 필요없음
		.and()
		.formLogin() // 파라미터를 받아서 로그인
		.loginPage("/auth/login") // 로그인 페이지 get요청(view)
		.loginProcessingUrl("/auth/login") // 로그인 post요청(PrincipalDetailsService -> loadUserByUsername() 호출)
		.defaultSuccessUrl("/search") // 로그인성공시 (학생 및 선생 판별후 알맞는 사이트로 리다이렉트됨)
		.failureUrl("/api/v1/auth/loginfail"); // 로그인실패시
	}
}
