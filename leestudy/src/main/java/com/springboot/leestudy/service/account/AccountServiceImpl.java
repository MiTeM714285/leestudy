package com.springboot.leestudy.service.account;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.leestudy.domain.user.UserRepository;
import com.springboot.leestudy.web.dto.account.PasswordCheckReqDto;
import com.springboot.leestudy.web.dto.account.UpdateUserCommonReqDto;
import com.springboot.leestudy.web.dto.account.UpdateUserStudentReqDto;
import com.springboot.leestudy.web.dto.account.UpdateUserTeacherReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final UserRepository userRepository;
	
	@Override
	public boolean checkPassword(PasswordCheckReqDto passwordCheckReqDto) throws Exception {
		String currentPassword = userRepository.findPasswordByUsername(passwordCheckReqDto.getUsername());
		
		boolean result = new BCryptPasswordEncoder().matches(passwordCheckReqDto.getPassword(), currentPassword);
		System.out.println(result);
		return result;
	}

	@Override
	public boolean updateUserCommonByUsername(UpdateUserCommonReqDto updateUserCommonReqDto) throws Exception {
		if(updateUserCommonReqDto.getPassword().equals("") || updateUserCommonReqDto.getPassword() == null) { // 비밀번호 제외하고 업데이트시
			return userRepository.updateUserCommonByUsernameWithoutPassword(updateUserCommonReqDto.toEntity()) > 0;
		} else { // 비밀번호 포함 업데이트시
			return userRepository.updateUserCommonByUsername(updateUserCommonReqDto.toEntity()) > 0;
		}
	}

	@Override
	public boolean updateUserStudentByUsername(UpdateUserStudentReqDto updateUserStudentReqDto) throws Exception {
		if(updateUserStudentReqDto.getStudent_phonenum().equals("") || updateUserStudentReqDto.getStudent_phonenum() == null) { // 전화번호 제외하고 업데이트시
			return userRepository.updateUserStudentByUsernameWithoutPhonenum(updateUserStudentReqDto.toEntity()) > 0;
		} else {
			return userRepository.updateUserStudentByUsername(updateUserStudentReqDto.toEntity()) > 0;
		}
	}

	@Override
	public boolean updateUserTeacherByUsername(UpdateUserTeacherReqDto updateUserTeacherReqDto) throws Exception {
		if(updateUserTeacherReqDto.getTeacher_phonenum().equals("") || updateUserTeacherReqDto.getTeacher_phonenum() == null) { // 전화번호 제외하고 업데이트시
			return userRepository.updateUserTeacherByUsernameWithoutPhonenum(updateUserTeacherReqDto.toEntity()) > 0;
		} else {
			return userRepository.updateUserTeacherByUsername(updateUserTeacherReqDto.toEntity()) > 0;
		}
	}

	@Override
	public boolean deleteUserCommonByUsername(String username) throws Exception {
		return userRepository.deleteUserCommonByUsername(username) > 0;
	}

	@Override
	public int countUserCommonByRole(String role) throws Exception {
		return userRepository.countUserCommonByRole(role);
	}
}
