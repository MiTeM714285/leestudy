package com.springboot.leestudy.web.dto.account;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springboot.leestudy.domain.user.Entity.UserCommon;

import lombok.Data;

@Data
public class UpdateUserCommonReqDto {
	
	@NotBlank
	private String username;
	
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
	
	public UserCommon toEntity() {
		if (password.equals("") || password == null ) {
			return UserCommon.builder()
					.username(username)
					.password(null)
					.nickname(nickname)
					.gender(gender)
					.age(age)
					.address_part1(address_part1)
					.address_part2(address_part2)
					.build();
		} else {
			return UserCommon.builder()
					.username(username)
					.password(new BCryptPasswordEncoder().encode(password))
					.nickname(nickname)
					.gender(gender)
					.age(age)
					.address_part1(address_part1)
					.address_part2(address_part2)
					.build();
		}
	}
}
