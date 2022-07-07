package com.springboot.leestudy.domain.review.Entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Review {
	
	private int review_code;
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
	private String student_picture;
	private String student_nickname;
	private String student_address_part1;
	private String student_address_part2;
	private String student_student_grade;
	private int review_score_professionality;
	private int review_score_readyness;
	private int review_score_teaching;
	private int review_score_ontime;
	private String review_content;
	private LocalDateTime review_createdate;

}
