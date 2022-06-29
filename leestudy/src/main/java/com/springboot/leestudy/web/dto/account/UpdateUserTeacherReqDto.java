package com.springboot.leestudy.web.dto.account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.springboot.leestudy.domain.user.Entity.UserStudent;
import com.springboot.leestudy.domain.user.Entity.UserTeacher;

import lombok.Data;

@Data
public class UpdateUserTeacherReqDto {
	@NotBlank
	private String username;
	@NotBlank
	private String teacher_email;
	
	private String teacher_introduction;
	@NotNull // int형식은 @NotBlank 사용불가
	private int teacher_university;

	private String teacher_university_isgraduate;

	private String teacher_university_major;

	private String teacher_university_studentnum;

	private String teacher_phonenum;
	@NotBlank
	private String teacher_price;
	@NotBlank
	private String teacher_detailprice;
	@NotBlank
	private String teacher_personality;
	@NotNull // int형식은 @NotBlank 사용불가
	private int teacher_available_remote;
	@NotBlank
	private String teacher_subject;
	@NotBlank
	private String teacher_available_time;
	@NotBlank
	private String teacher_teaching_detail;
	@NotBlank
	private String teacher_teaching_style;
	@NotBlank
	private String teacher_available_demonstration;
	
	public UserTeacher toEntity() {
		if (teacher_phonenum.equals("") || teacher_phonenum == null ) { // 전화번호를 수정하지 않았다면
			return UserTeacher.builder()
					.username(username)
					.teacher_email(teacher_email)
					.teacher_introduction(teacher_introduction)
					.teacher_university(teacher_university)
					.teacher_university_isgraduate(teacher_university_isgraduate)
					.teacher_university_major(teacher_university_major)
					.teacher_university_studentnum(teacher_university_studentnum)
					.teacher_phonenum(teacher_phonenum)
					.teacher_price(teacher_price)
					.teacher_detailprice(teacher_detailprice)
					.teacher_personality(teacher_personality)
					.teacher_available_remote(teacher_available_remote)
					.teacher_subject(teacher_subject)
					.teacher_available_time(teacher_available_time)
					.teacher_teaching_detail(teacher_teaching_detail)
					.teacher_teaching_style(teacher_teaching_style)
					.teacher_available_demonstration(teacher_available_demonstration)
					.build();
		} else { // 전화번호를 수정하였다면
			return UserTeacher.builder()
					.username(username)
					.teacher_email(teacher_email)
					.teacher_introduction(teacher_introduction)
					.teacher_university(teacher_university)
					.teacher_university_isgraduate(teacher_university_isgraduate)
					.teacher_university_major(teacher_university_major)
					.teacher_university_studentnum(teacher_university_studentnum)
					.teacher_phonenum(teacher_phonenum)
					.teacher_price(teacher_price)
					.teacher_detailprice(teacher_detailprice)
					.teacher_personality(teacher_personality)
					.teacher_available_remote(teacher_available_remote)
					.teacher_subject(teacher_subject)
					.teacher_available_time(teacher_available_time)
					.teacher_teaching_detail(teacher_teaching_detail)
					.teacher_teaching_style(teacher_teaching_style)
					.teacher_available_demonstration(teacher_available_demonstration)
					.build();
		}
	}
}
