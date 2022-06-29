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
	
	private String username;
	private String teacher_email;
	private String teacher_phonenum;
	private String teacher_introduction;
	private int teacher_university;
	private String teacher_university_isgraduate;
	private String teacher_university_major;
	private String teacher_university_studentnum;
	private String teacher_price;
	private String teacher_detailprice;
	private String teacher_personality;
	private int teacher_available_remote;
	private String teacher_subject;
	private String teacher_available_time;
	private String teacher_teaching_detail;
	private String teacher_teaching_style;
	private String teacher_available_demonstration;
}
