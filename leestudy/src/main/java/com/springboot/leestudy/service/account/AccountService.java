package com.springboot.leestudy.service.account;
import com.springboot.leestudy.web.dto.account.PasswordCheckReqDto;
import com.springboot.leestudy.web.dto.account.UpdateUserCommonReqDto;
import com.springboot.leestudy.web.dto.account.UpdateUserStudentReqDto;
import com.springboot.leestudy.web.dto.account.UpdateUserTeacherReqDto;

public interface AccountService {

	public boolean checkPassword(PasswordCheckReqDto passwordCheckReqDto) throws Exception;
	public boolean updateUserCommonByUsername(UpdateUserCommonReqDto updateUserCommonReqDto) throws Exception;
	public boolean updateUserStudentByUsername(UpdateUserStudentReqDto updateUserStudentReqDto) throws Exception;
	public boolean updateUserTeacherByUsername(UpdateUserTeacherReqDto updateUserTeacherReqDto) throws Exception;
	public boolean deleteUserCommonByUsername(String username) throws Exception;
	public int countUserCommonByRole(String role) throws Exception;
}
