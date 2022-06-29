package com.springboot.leestudy.web.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.springboot.leestudy.domain.user.Entity.UserStudent;

import lombok.Data;

@Data
public class SaveUserStudentDto {
	
	@NotBlank
	private String username;
	@NotBlank
	private String student_email;
	@NotBlank
	private String student_phonenum;
	@NotNull // int형식은 @NotBlank 사용불가
	private int student_isurgent;
	@NotBlank
	private String student_student_grade;
	@NotBlank
	private String student_subject;
	@NotBlank
	private String student_price;
	@NotBlank
	private String student_available_time;
	@NotNull // int형식은 @NotBlank 사용불가
	private int student_available_remote;
	
	private String student_request;
	
	public UserStudent toEntity() {
		return UserStudent.builder()
			.username(username)
			.student_email(student_email)
			.student_phonenum(student_phonenum)
			.student_isurgent(student_isurgent)
			.student_student_grade(student_student_grade)
			.student_subject(student_subject)
			.student_price(student_price)
			.student_available_remote(student_available_remote)
			.student_available_time(student_available_time)
			.student_request(student_request)
			.build();
	}
}
