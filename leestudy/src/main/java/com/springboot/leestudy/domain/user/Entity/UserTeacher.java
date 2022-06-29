package com.springboot.leestudy.domain.user.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserTeacher {
	
	private int teachercode;
	private String username;
	private String teacherEmail;
	private String teacherIntroduction;
	private int teacherUniversity;
	private String teacherUniversityIsgraduate;
	private String teacherUniversityMajor;
	private String teacherUniversityStudentnum;
	private String teacherPhonenum;
	private String teacherPrice;
	private String teacherDetailprice;
	private String teacherPersonality;
	private int teacherAvailableRemote;
	private String teacherSubject;
	private String teacherAvailableTime;
	private String teacherTeachingDetail;
	private String teacherTeachingStyle;
	private String teacherAvailableDemonstration;
}
