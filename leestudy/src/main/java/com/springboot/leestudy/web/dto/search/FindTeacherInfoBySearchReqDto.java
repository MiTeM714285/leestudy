package com.springboot.leestudy.web.dto.search;

import com.springboot.leestudy.domain.user.Entity.UserTeacherAll;

import lombok.Data;

@Data
public class FindTeacherInfoBySearchReqDto {
	private String address_part1;
	private String address_part2;
	private String teacher_subject;
	private String teacher_available_remote;
	private String gender;
	private String teacher_price;
	private String age;
	
	public UserTeacherAll toEntity() {
		return UserTeacherAll.builder()
			.address_part1(address_part1)
			.address_part2(address_part2)
			.teacher_subject(teacher_subject)
			.teacher_available_remote(teacher_available_remote)
			.gender(gender)
			.teacher_price(teacher_price)
			.age(age)
			.build();
	}
}
