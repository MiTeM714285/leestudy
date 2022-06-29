package com.springboot.leestudy.service.auth;

import com.springboot.leestudy.domain.user.Entity.UserCommon;
import com.springboot.leestudy.web.dto.auth.SaveUserCommonDto;
import com.springboot.leestudy.web.dto.auth.SaveUserStudentDto;
import com.springboot.leestudy.web.dto.auth.SaveUserTeacherDto;

public interface AuthService {
	
	public boolean checkUsername(String username) throws Exception;
	public boolean saveUserCommon(String role, SaveUserCommonDto saveUserCommonDto) throws Exception;
	public boolean saveUserStudent(SaveUserStudentDto saveUserStudentDto) throws Exception;
	public boolean saveUserTeacher(SaveUserTeacherDto saveUserTeacherDto) throws Exception;
	public UserCommon findUserCommonByUsername(String username) throws Exception;
}
