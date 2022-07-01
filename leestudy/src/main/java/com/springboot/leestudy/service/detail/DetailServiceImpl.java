package com.springboot.leestudy.service.detail;

import org.springframework.stereotype.Service;

import com.springboot.leestudy.domain.user.UserRepository;
import com.springboot.leestudy.web.dto.detail.FindStudentInfoByDetailRespDto;
import com.springboot.leestudy.web.dto.detail.FindTeacherInfoByDetailRespDto;
import com.springboot.leestudy.web.dto.detail.FindUserInfoByDetailReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetailServiceImpl implements DetailService {

	private final UserRepository userRepository;
	
	@Override
	public FindTeacherInfoByDetailRespDto findTeacherInfoByDetail(
			String username) throws Exception {
			//String username = findUserInfoByDetailReqDto.getUsername();
		return userRepository.findTeacherInfoByDetail(username).toDetailRespDto();
	}

	@Override
	public FindStudentInfoByDetailRespDto findStudentInfoByDetail(
			String username) throws Exception {
		//String username = findUserInfoByDetailReqDto.getUsername();
		return userRepository.findStudentInfoByDetail(username).toDetailRespDto();
	}
}
