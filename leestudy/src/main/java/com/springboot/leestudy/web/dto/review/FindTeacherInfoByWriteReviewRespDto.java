package com.springboot.leestudy.web.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FindTeacherInfoByWriteReviewRespDto {
	
	private String teacher_nickname;
	private String teacher_picture;
	private int teacher_university;
	private String university_name;
	private String teacher_university_isgraduate;
	private String teacher_university_major;
	private String teacher_university_studentnum;
	private String teacher_price;
	private String teacher_gender;
}
