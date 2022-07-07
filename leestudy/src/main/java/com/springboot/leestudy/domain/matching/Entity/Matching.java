package com.springboot.leestudy.domain.matching.Entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Matching {
	private int matching_code;
	private String picture;
	private String nickname;
	private String student_name;
	private String teacher_name;
	private int student_isready;
	private int teacher_isready;
	private int matching_isend;
	private LocalDateTime matching_startdate;
	private LocalDateTime matching_enddate;
	private boolean review_exist;
}
