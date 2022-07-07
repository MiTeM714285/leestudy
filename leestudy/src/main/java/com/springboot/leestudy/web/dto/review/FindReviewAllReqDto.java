package com.springboot.leestudy.web.dto.review;

import com.springboot.leestudy.domain.review.Entity.Review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FindReviewAllReqDto {
	
	private String student_address_part1;
	private String student_address_part2;
	private String teacher_name;
	private String student_name;
	
	public Review toEntity() {
		return Review.builder()
			.student_address_part1(student_address_part1)
			.student_address_part2(student_address_part2)
			.teacher_name(teacher_name)
			.student_name(student_name)
			.build();
	}

}
