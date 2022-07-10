package com.springboot.leestudy.web.dto.review;

import java.time.LocalDateTime;


import com.springboot.leestudy.domain.review.Entity.Review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaveReviewReqDto {
	private int matching_code;
	private String teacher_name;
	private String student_name;
	private LocalDateTime matching_startdate;
	private String teacher_nickname;
	private String teacher_picture;
	private int teacher_university;
	private String university_name;
	private String teacher_university_isgraduate;
	private String teacher_university_major;
	private String teacher_university_studentnum;
	private String teacher_price;
	private String teacher_gender;
	private String student_nickname;
	private String student_picture;
	private String student_address_part1;
	private String student_address_part2;
	private String student_student_grade;
	private int review_score_professionality;
	private int review_score_readyness;
	private int review_score_teaching;
	private int review_score_ontime;
	private String review_content;
	
	public Review toEntity() {
		return Review.builder()
			.matching_code(matching_code)
			.teacher_name(teacher_name)
			.student_name(student_name)
			.matching_startdate(matching_startdate)
			.teacher_nickname(teacher_nickname)
			.teacher_picture(teacher_picture)
			.teacher_university(teacher_university)
			.university_name(university_name)
			.teacher_university_isgraduate(teacher_university_isgraduate)
			.teacher_university_major(teacher_university_major)
			.teacher_university_studentnum(teacher_university_studentnum)
			.teacher_price(teacher_price)
			.teacher_gender(teacher_gender)
			.student_nickname(student_nickname)
			.student_picture(student_picture)
			.student_address_part1(student_address_part1)
			.student_address_part2(student_address_part2)
			.student_student_grade(student_student_grade)
			.review_score_professionality(review_score_professionality)
			.review_score_readyness(review_score_readyness)
			.review_score_teaching(review_score_teaching)
			.review_score_ontime(review_score_ontime)
			.review_content(review_content)
			.build();
	}
}
