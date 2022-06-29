package com.springboot.leestudy.config.auth;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.leestudy.domain.user.Entity.UserCommon;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private UserCommon userCommon;
	private Map<String, Object> attributes;
	
	public PrincipalDetails(UserCommon userCommon) {
		this.userCommon = userCommon;
	}
	
	public PrincipalDetails(UserCommon userCommon, Map<String, Object> attributes) {
		this.userCommon = userCommon;
		this.attributes = attributes;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userCommon.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userCommon.getUsername();
	}
	
	public String getRole() { // 학생과 선생님을 판단하여 각각 다른 페이지로 이동하고자 하는 메소드
		return userCommon.getRole();
	}

	@Override
	public boolean isAccountNonExpired() { // 계정이 만료되었는지 확인 (false일시 로그인불가)
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // 비밀번호가 지정한 횟수 이상 틀릴시 잠김 (false일시 로그인불가)
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // 자격증명 만료시 계정사용 불가 (false일시 로그인불가)
		// 해당 계정의 라이센스 별도 발급
		return true;
	}

	@Override
	public boolean isEnabled() { // 휴면계정 (false일시 로그인불가)
		// TODO Auto-generated method stub
		return true;
	
	}
}
