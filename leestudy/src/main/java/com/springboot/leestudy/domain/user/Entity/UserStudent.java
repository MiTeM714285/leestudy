package com.springboot.leestudy.domain.user.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserStudent {
	
	private int studentcode;
	private String username;
	private String studentEmail;
	private String studentPhonenum;
	private int studentIsurgent;
	private String studentGrade;
	private String studentSubject;
	private String studentPrice;
	private String studentAvailableTime;
	private int studentAvailableRemote;
	private String studentRequest;
}
