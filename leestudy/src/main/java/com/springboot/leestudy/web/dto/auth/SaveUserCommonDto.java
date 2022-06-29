package com.springboot.leestudy.web.dto.auth;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springboot.leestudy.domain.user.Entity.UserCommon;
import lombok.Data;

@Data
public class SaveUserCommonDto {
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String nickname;
	@NotBlank
	private String gender;
	@NotBlank
	private String age;
	@NotBlank
	private String address_part1;
	@NotBlank
	private String address_part2;
	//private String role;
	
	public UserCommon toEntity(String role) {
		return UserCommon.builder()
			.username(username)
			.password(new BCryptPasswordEncoder().encode(password))
			.nickname(nickname)
			.gender(gender)
			.age(age)
			.address_part1(address_part1)
			.address_part2(address_part2)
			.role(role)
			.build();
	}
}
