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
	private String studentEmail;
	@NotBlank
	private String studentPhonenum;
	@NotNull // int형식은 @NotBlank 사용불가
	private int studentIsurgent;
	@NotBlank
	private String studentGrade;
	@NotBlank
	private String studentSubject;
	@NotBlank
	private String studentPrice;
	@NotBlank
	private String studentAvailableTime;
	@NotNull // int형식은 @NotBlank 사용불가
	private int studentAvailableRemote;
	
	private String studentRequest;
	
	public UserStudent toEntity() {
		return UserStudent.builder()
			.username(username)
			.studentEmail(studentEmail)
			.studentPhonenum(studentPhonenum)
			.studentIsurgent(studentIsurgent)
			.studentGrade(studentGrade)
			.studentSubject(studentSubject)
			.studentPrice(studentPrice)
			.studentAvailableRemote(studentAvailableRemote)
			.studentAvailableTime(studentAvailableTime)
			.studentRequest(studentRequest)
			.build();
	}
}
