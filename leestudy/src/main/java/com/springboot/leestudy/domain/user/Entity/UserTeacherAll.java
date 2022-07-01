package com.springboot.leestudy.domain.user.Entity;

import java.time.LocalDateTime;

import com.springboot.leestudy.web.dto.detail.FindTeacherInfoByDetailRespDto;
import com.springboot.leestudy.web.dto.search.FindTeacherInfoBySearchRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserTeacherAll {
	private String username;
	private String nickname; 
	private String picture; 
	private int review_count;
	private String gender; 
	private String age; 
	private String address_part1; 
	private String address_part2; 
	private int isactive; 
	private String role;
	private LocalDateTime createdate;
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
	private String teacher_available_remote;
	private String teacher_subject;
	private String teacher_available_time;
	private String teacher_teaching_detail;
	private String teacher_teaching_style;
	private String teacher_available_demonstration;
	private String university_name;
	private String university_addr1;
	private String university_addr2;
	private String university_type;
	private String university_campus;
	private String university_foundation;
	
	public FindTeacherInfoBySearchRespDto toSearchRespDto() {
		return FindTeacherInfoBySearchRespDto.builder()
			.username(username)
			.nickname(nickname)
			.picture(picture)
			.review_count(review_count)
			.address_part1(address_part1)
			.address_part2(address_part2)
			.gender(gender)
			.teacher_subject(teacher_subject)
			.university_name(university_name == null ? "출신대학교 없음" : university_name)
			.build();
	}
	
	public FindTeacherInfoByDetailRespDto toDetailRespDto() {
		return FindTeacherInfoByDetailRespDto.builder()
			.username(username)
			.picture(picture)
			.nickname(nickname)
			.university_name(university_name)
			.teacher_university_studentnum(teacher_university_studentnum)
			.teacher_university_isgraduate(teacher_university_isgraduate)
            .teacher_university_major(teacher_university_major)
			.gender(gender)
			.teacher_subject(teacher_subject)
			.teacher_available_remote(teacher_available_remote)
			.teacher_price(teacher_price)
			.address_part1(address_part1)
			.address_part2(address_part2)
			.teacher_phonenum(teacher_phonenum)
			.teacher_introduction(teacher_introduction)
			.teacher_personality(teacher_personality)
			.teacher_available_time(teacher_available_time)
			.age(age)
			.teacher_detailprice(teacher_detailprice)
			.teacher_teaching_style(teacher_teaching_style)
			.teacher_teaching_detail(teacher_teaching_detail)
			.createdate(createdate)
			.build();
	}
}
