package com.springboot.leestudy.web.dto.detail;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FindStudentInfoByDetailRespDto {
	private int usercode;
	private String username;
	private String picture; 
	private String nickname; 
	private String student_student_grade;
	private String student_subject;
	private String student_available_remote;
	private String student_price;
	private String address_part1; 
	private String address_part2; 
	private String student_phonenum;
	private String student_available_time;
	private String gender; 
	private String age; 
	private String student_request;
	private LocalDateTime createdate;
}