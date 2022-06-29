package com.springboot.leestudy.web.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.springboot.leestudy.domain.user.Entity.UserTeacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaveUserTeacherDto {
	
	@NotBlank
	private String username;
	@NotBlank
	private String teacherEmail;
	
	private String teacherIntroduction;
	@NotNull // int형식은 @NotBlank 사용불가
	private int teacherUniversity;

	private String teacherUniversityIsgraduate;

	private String teacherUniversityMajor;

	private String teacherUniversityStudentnum;
	@NotBlank
	private String teacherPhonenum;
	@NotBlank
	private String teacherPrice;
	@NotBlank
	private String teacherDetailprice;
	@NotBlank
	private String teacherPersonality;
	@NotNull // int형식은 @NotBlank 사용불가
	private int teacherAvailableRemote;
	@NotBlank
	private String teacherSubject;
	@NotBlank
	private String teacherAvailableTime;
	@NotBlank
	private String teacherTeachingDetail;
	@NotBlank
	private String teacherTeachingStyle;
	@NotBlank
	private String teacherAvailableDemonstration;
	
	public UserTeacher toEntity() {
		return UserTeacher.builder()
			.username(username)
			.teacherEmail(teacherEmail)
			.teacherIntroduction(teacherIntroduction)
			.teacherUniversity(teacherUniversity)
			.teacherUniversityIsgraduate(teacherUniversityIsgraduate)
			.teacherUniversityMajor(teacherUniversityMajor)
			.teacherUniversityStudentnum(teacherUniversityStudentnum)
			.teacherPhonenum(teacherPhonenum)
			.teacherPrice(teacherPrice)
			.teacherDetailprice(teacherDetailprice)
			.teacherPersonality(teacherPersonality)
			.teacherAvailableRemote(teacherAvailableRemote)
			.teacherSubject(teacherSubject)
			.teacherAvailableTime(teacherAvailableTime)
			.teacherTeachingDetail(teacherTeachingDetail)
			.teacherTeachingStyle(teacherTeachingStyle)
			.teacherAvailableDemonstration(teacherAvailableDemonstration)
			.build();
	}
}
