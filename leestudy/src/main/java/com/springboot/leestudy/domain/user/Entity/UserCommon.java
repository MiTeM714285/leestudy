package com.springboot.leestudy.domain.user.Entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserCommon {
	private int usercode;
	private String username;
	private String password;
	private String nickname;
	private String picture;
	private String gender;
	private String age;
	private String address_part1;
	private String address_part2;
	private boolean isactive;
	private String role;
	private LocalDateTime createdate;
	private LocalDateTime updatedate;
}
