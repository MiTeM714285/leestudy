package com.springboot.leestudy.web.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FindStudentInfoByWriteReviewRespDto {
	
	private String student_nickname;
	private String student_picture;
	private String student_address_part1;
	private String student_address_part2;
	private String student_student_grade;
}
