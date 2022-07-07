package com.springboot.leestudy.domain.user.Entity;

import java.time.LocalDateTime;

import com.springboot.leestudy.web.dto.detail.FindStudentInfoByDetailRespDto;
import com.springboot.leestudy.web.dto.review.FindStudentInfoByWriteReviewRespDto;
import com.springboot.leestudy.web.dto.search.FindStudentInfoBySearchRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserStudentAll {
	private int usercode;
	private String username;
	private String nickname; 
	private String picture; 
	private String gender; 
	private String age; 
	private String address_part1; 
	private String address_part2; 
	private int isactive; 
	private String role;
	private LocalDateTime createdate;
	private String student_email;
	private String student_phonenum;
	private int student_isurgent;
	private String student_student_grade;
	private String student_subject;
	private String student_price;
	private String student_available_time;
	private String student_available_remote;
	private String student_request;
	
	public FindStudentInfoBySearchRespDto toSearchRespDto() {
		return FindStudentInfoBySearchRespDto.builder()
			.username(username)
			.nickname(nickname)
			.picture(picture)
			.address_part1(address_part1)
			.address_part2(address_part2)
			.gender(gender)
			.student_subject(student_subject)
			.student_student_grade(student_student_grade)
			.build();
	}
	
	public FindStudentInfoByDetailRespDto toDetailRespDto() {
		return FindStudentInfoByDetailRespDto.builder()
			.usercode(usercode)
			.username(username)
			.picture(picture)
			.nickname(nickname)
			.student_student_grade(student_student_grade)
			.student_subject(student_subject)
			.student_available_remote(student_available_remote)
			.student_price(student_price)
			.address_part1(address_part1)
			.address_part2(address_part2)
			.student_phonenum(student_phonenum)
			.student_available_time(student_available_time)
			.gender(gender)
			.age(age)
			.student_request(student_request)
			.createdate(createdate)
			.build();
	}
	
	public FindStudentInfoByWriteReviewRespDto toWriteReviewRespDto() {
		return FindStudentInfoByWriteReviewRespDto.builder()
			.student_nickname(nickname)
			.student_picture(picture)
			.student_address_part1(address_part1)
			.student_address_part2(address_part2)
			.student_student_grade(student_student_grade)
			.build();
	}
}
