package com.springboot.leestudy.web.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.leestudy.service.search.SearchService;
import com.springboot.leestudy.web.dto.CustomResponseDto;
import com.springboot.leestudy.web.dto.search.FindStudentInfoBySearchReqDto;
import com.springboot.leestudy.web.dto.search.FindTeacherInfoBySearchRespDto;
import com.springboot.leestudy.web.dto.search.FindTeacherInfoBySearchReqDto;
import com.springboot.leestudy.web.dto.search.FindStudentInfoBySearchRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class SearchController {
	
	private final SearchService searchService;
	
	@PostMapping("/search-teacher")
	public ResponseEntity<?> findTeacherInfoBySearch(@Valid @RequestBody FindTeacherInfoBySearchReqDto findTeacherInfoBySearchReqDto, BindingResult bindingResult) throws Exception {
		List<FindTeacherInfoBySearchRespDto> findTeacherInfoBySearchRespDtos = searchService.findTeacherInfoBySearch(findTeacherInfoBySearchReqDto);
		return new ResponseEntity<>(new CustomResponseDto<List<FindTeacherInfoBySearchRespDto>>(1, "선생 search 조회", findTeacherInfoBySearchRespDtos), HttpStatus.OK);
	}
	
	@PostMapping("/search-student")
	public ResponseEntity<?> findStudentInfoBySearch(@Valid @RequestBody FindStudentInfoBySearchReqDto findStudentInfoBySearchReqDto, BindingResult bindingResult) throws Exception {
		List<FindStudentInfoBySearchRespDto> findStudentInfoBySearchRespDtos = searchService.findStudentInfoBySearch(findStudentInfoBySearchReqDto);
		return new ResponseEntity<>(new CustomResponseDto<List<FindStudentInfoBySearchRespDto>>(1, "선생 search 조회", findStudentInfoBySearchRespDtos), HttpStatus.OK);
	}
}
