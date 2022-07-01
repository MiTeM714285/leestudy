package com.springboot.leestudy.domain.user.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserStudent {
	
	private String username;
	private String student_email;
	private String student_phonenum;
	private int student_isurgent;
	private String student_student_grade;
	private String student_subject;
	private String student_price;
	private String student_available_time;
	private String student_available_remote;
	private String student_request;
}
