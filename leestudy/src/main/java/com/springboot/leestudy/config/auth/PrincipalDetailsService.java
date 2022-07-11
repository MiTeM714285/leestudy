package com.springboot.leestudy.config.auth;

import java.util.HashMap;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.leestudy.domain.user.UserRepository;
import com.springboot.leestudy.domain.user.Entity.UserCommon;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // 로그인 요청시 자동요청
		
		UserCommon userCommonEntity = null;
		try {
			userCommonEntity = userRepository.findUserCommonByUsername(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(userCommonEntity);
		System.out.println("로그인 요청");
		
		if(userCommonEntity == null) {
			return null; // uri는 auth/signin?error
		}
		
		return new PrincipalDetails(userCommonEntity, new HashMap<String, Object>()); // Authentication 세션 저장 역할
	}
}
