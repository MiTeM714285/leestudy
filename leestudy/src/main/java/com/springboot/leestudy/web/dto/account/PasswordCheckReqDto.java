package com.springboot.leestudy.web.dto.account;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PasswordCheckReqDto {
	@NotBlank
	private String username;
	@NotBlank
	private String password;
}
