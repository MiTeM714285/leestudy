package com.springboot.leestudy.web.controller.api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.leestudy.service.detail.DetailService;
import com.springboot.leestudy.web.dto.CustomResponseDto;
import com.springboot.leestudy.web.dto.detail.FindStudentInfoByDetailRespDto;
import com.springboot.leestudy.web.dto.detail.FindTeacherInfoByDetailRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class DetailController {
	
	private final DetailService detailService;
	
	@PostMapping("/detail-teacher")
	public ResponseEntity<?> findTeacherInfoByDetail(String username) throws Exception {
		FindTeacherInfoByDetailRespDto findTeacherInfoByDetailRespDto = detailService.findTeacherInfoByDetail(username);
		return new ResponseEntity<>(new CustomResponseDto<FindTeacherInfoByDetailRespDto>(1, "선생 detail 조회", findTeacherInfoByDetailRespDto), HttpStatus.OK);
	}
	
	@PostMapping("/detail-student")
	public ResponseEntity<?> findStudentInfoByDetail(String username) throws Exception {
		FindStudentInfoByDetailRespDto findStudentInfoByDetailRespDto = detailService.findStudentInfoByDetail(username);
		return new ResponseEntity<>(new CustomResponseDto<FindStudentInfoByDetailRespDto>(1, "학생 detail 조회", findStudentInfoByDetailRespDto), HttpStatus.OK);
	}
}
