package com.springboot.leestudy.web.dto.account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springboot.leestudy.domain.user.Entity.UserCommon;
import com.springboot.leestudy.domain.user.Entity.UserStudent;

import lombok.Data;

@Data
public class UpdateUserStudentReqDto {
	@NotBlank
	private String username;
	@NotBlank
	private String student_email;

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
		if (student_phonenum.equals("") || student_phonenum == null ) { // 전화번호를 수정하지 않았다면
			return UserStudent.builder()
					.username(username)
					.student_email(student_email)
					.student_phonenum(null)
					.student_isurgent(student_isurgent)
					.student_student_grade(student_student_grade)
					.student_subject(student_subject)
					.student_price(student_price)
					.student_available_time(student_available_time)
					.student_available_remote(student_available_remote)
					.student_request(student_request)
					.build();
		} else { // 전화번호를 수정하였다면
			return UserStudent.builder()
					.username(username)
					.student_email(student_email)
					.student_phonenum(student_phonenum)
					.student_isurgent(student_isurgent)
					.student_student_grade(student_student_grade)
					.student_subject(student_subject)
					.student_price(student_price)
					.student_available_time(student_available_time)
					.student_available_remote(student_available_remote)
					.student_request(student_request)
					.build();
		}
	}
}
