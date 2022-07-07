package com.springboot.leestudy.web.dto.matching;

import javax.validation.constraints.NotBlank;

import com.springboot.leestudy.domain.matching.Entity.Matching;

import lombok.Data;

@Data
public class SaveMatchingDto {
	
	@NotBlank
	private String student_name;
	@NotBlank
	private String teacher_name;
	
	public Matching toEntity() {
		return Matching.builder()
			.student_name(student_name)
			.teacher_name(teacher_name)
			.build();
	}
}
