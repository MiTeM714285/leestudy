package com.springboot.leestudy.web.dto.detail;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class FindUserInfoByDetailReqDto {
	@NotBlank
	private String username;
}
