package com.springboot.leestudy.service.auth;

import org.springframework.stereotype.Service;

import com.springboot.leestudy.domain.user.UserRepository;
import com.springboot.leestudy.domain.user.Entity.UserCommon;
import com.springboot.leestudy.domain.user.Entity.UserStudent;
import com.springboot.leestudy.domain.user.Entity.UserTeacher;
import com.springboot.leestudy.web.dto.auth.SaveUserCommonDto;
import com.springboot.leestudy.web.dto.auth.SaveUserStudentDto;
import com.springboot.leestudy.web.dto.auth.SaveUserTeacherDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	
	@Override
	public boolean checkUsername(String username) throws Exception {
		return userRepository.findUserCommonByUsername(username) == null ? true : false;
	}

	@Override
	public boolean saveUserCommon(String role, SaveUserCommonDto saveUserCommonDto) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.saveUserCommon(saveUserCommonDto.toEntity(role)) > 0 ? true : false;
	}

	@Override
	public boolean saveUserStudent(SaveUserStudentDto saveUserStudentDto) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.saveUserStudent(saveUserStudentDto.toEntity()) > 0 ? true : false;
	}

	@Override
	public boolean saveUserTeacher(SaveUserTeacherDto saveUserTeacherDto) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.saveUserTeacher(saveUserTeacherDto.toEntity()) > 0 ? true : false;
	}

	@Override
	public UserCommon findUserCommonByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findUserCommonByUsername(username);
	}

	@Override
	public UserStudent findUserStudentByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findUserStudentByUsername(username);
	}

	@Override
	public UserTeacher findUserTeacherByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findUserTeacherByUsername(username);
	}
}
