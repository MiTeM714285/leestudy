package com.springboot.leestudy.web.dto.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FindStudentInfoBySearchRespDto {
	private String username;
	private String nickname; 
	private String picture;
	private String address_part1;
	private String address_part2;
	private String gender;
	private String student_subject;
	private String student_student_grade;
}
