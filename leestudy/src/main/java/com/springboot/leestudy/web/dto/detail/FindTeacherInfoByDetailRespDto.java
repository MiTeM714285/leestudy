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
public class FindTeacherInfoByDetailRespDto {
	private String username;
	private String picture;
	private String nickname;
	private String university_name;
	private String teacher_university_studentnum;
	private String teacher_university_isgraduate;
	private String teacher_university_major;
	private String gender;
	private String teacher_subject;
	private String teacher_available_remote;
	private String teacher_price;
	private String address_part1;
	private String address_part2;
	private String teacher_phonenum;
	private String teacher_introduction;
	private String teacher_personality;
	private String teacher_available_time;
	private String age;
	private String teacher_detailprice;
	private String teacher_teaching_detail;
	private String teacher_teaching_style;
	private LocalDateTime createdate;
}
