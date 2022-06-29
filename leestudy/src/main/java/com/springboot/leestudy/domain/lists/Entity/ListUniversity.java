package com.springboot.leestudy.domain.lists.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ListUniversity {
	
	private int university_code;
	private String university_name;
	private String university_addr1;
	private String university_addr2;
	private String university_type;
	private String university_campus;
	private String university_foundation;

}
