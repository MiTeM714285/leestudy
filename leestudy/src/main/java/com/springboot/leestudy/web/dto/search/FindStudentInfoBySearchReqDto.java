package com.springboot.leestudy.web.dto.search;

import com.springboot.leestudy.domain.user.Entity.UserStudentAll;

import lombok.Data;

@Data
public class FindStudentInfoBySearchReqDto {
	private String address_part1;
	private String address_part2;
	private String student_subject;
	private String student_available_remote;
	private String gender;
	private String student_price;
	private String age;
	
	public UserStudentAll toEntity() {
		return UserStudentAll.builder()
			.address_part1(address_part1)
			.address_part2(address_part2)
			.student_subject(student_subject)
			.student_available_remote(student_available_remote)
			.gender(gender)
			.student_price(student_price)
			.age(age)
			.build();
	}
}
