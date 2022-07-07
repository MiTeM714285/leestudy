package com.springboot.leestudy.web.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.leestudy.config.auth.PrincipalDetails;
import com.springboot.leestudy.domain.matching.Entity.Matching;
import com.springboot.leestudy.service.matching.MatchingService;
import com.springboot.leestudy.web.dto.CustomResponseDto;
import com.springboot.leestudy.web.dto.matching.SaveMatchingDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/matching")
@RequiredArgsConstructor
public class MatchingController {
	
	private final MatchingService matchingService;
	
	@PostMapping("/savematching")
	public ResponseEntity<?> saveMatching(@Valid @RequestBody SaveMatchingDto saveMatchingDto, BindingResult bindingResult) throws Exception {
		boolean result = matchingService.saveMatching(saveMatchingDto);
		
		if(result) {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(1, "매칭리스트 등록 완료", result), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(-1, "매칭리스트 등록 실패", result), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/checkmatching")
	public ResponseEntity<?> checkMatchingIsExist(@Valid @RequestBody SaveMatchingDto saveMatchingDto, BindingResult bindingResult) throws Exception {
		boolean result = matchingService.checkMatchingIsExist(saveMatchingDto);
		return new ResponseEntity<>(new CustomResponseDto<Boolean>(1, "매칭 중복검사 조회", result), HttpStatus.OK);
	}
	

	
	@GetMapping("/matchinglist") // 유저(학생 및 선생) 매칭 정보 조회
	public ResponseEntity<?> getMatchingList(@AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {
		String role = principalDetails.getRole();
		if(role.equals("USER_STUDENT")) {
			List<Matching> matchings = matchingService.findMatchingByStudentName(principalDetails.getUsername());
			return new ResponseEntity<>(new CustomResponseDto<List<Matching>>(1, "매칭 리스트 정보 - UserStudent", matchings), HttpStatus.OK);
		} else {
			List<Matching> matchings = matchingService.findMatchingByTeacherName(principalDetails.getUsername());
			return new ResponseEntity<>(new CustomResponseDto<List<Matching>>(1, "매칭 리스트 정보 - UserTeacher", matchings), HttpStatus.OK);
		}
	}
	
	@GetMapping("/matchingcount") // 유저(학생 및 선생) 매칭 정보 조회
	public ResponseEntity<?> getMatchingCountList(@AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {
		String role = principalDetails.getRole();
		if(role.equals("USER_STUDENT")) {
			int count = matchingService.findMatchingCountByStudentName(principalDetails.getUsername());
			return new ResponseEntity<>(new CustomResponseDto<Integer>(1, "매칭 수 정보 - UserStudent", count), HttpStatus.OK);
		} else {
			int count = matchingService.findMatchingCountByTeacherName(principalDetails.getUsername());
			return new ResponseEntity<>(new CustomResponseDto<Integer>(1, "매칭 수 정보 - UserTeacher", count), HttpStatus.OK);
		}
	}
	
	@PutMapping("/updateisready") // 
	public ResponseEntity<?> updateIsReady(@Valid @RequestBody SaveMatchingDto saveMatchingDto, @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {
		String role = principalDetails.getRole();
		List<Boolean> results = new ArrayList<Boolean>();
		if(role.equals("USER_STUDENT")) {
			boolean result1 = matchingService.updateStudentIsReady(saveMatchingDto); // 우선 student_isready를 1로 업데이트
			results.add(result1);
			if (result1 == true) { // 위 업데이트가 성공시
				Matching matching = matchingService.findMatchingByStudentTeacherName(saveMatchingDto);
				if (matching.getStudent_isready() == 1 && matching.getTeacher_isready() == 1) {  // 학생과 선생 둘다 준비가 되어있다면
					boolean result2 = matchingService.updateStartDate(saveMatchingDto);  // 과외 시작일 등록
					results.add(result2);
				}
			}
			return new ResponseEntity<>(new CustomResponseDto<List<Boolean>>(1, "학생분 준비 체크함", results), HttpStatus.OK);
		} else {
			boolean result1 = matchingService.updateTeacherIsReady(saveMatchingDto);  // 우선 teacher_isready를 1로 업데이트
			results.add(result1);
			if (result1 == true) { // 위 업데이트가 성공시
				Matching matching = matchingService.findMatchingByStudentTeacherName(saveMatchingDto);
				if (matching.getStudent_isready() == 1 && matching.getTeacher_isready() == 1) { // 학생과 선생 둘다 준비가 되어있다면
					boolean result2 = matchingService.updateStartDate(saveMatchingDto); // 과외 시작일 등록
					results.add(result2);
				}
			}
			return new ResponseEntity<>(new CustomResponseDto<List<Boolean>>(1, "선생분 준비 체크함", results), HttpStatus.OK);
		}
	}
	
	@PutMapping("/updatestartdate")
	public ResponseEntity<?> updateStartDate(@Valid @RequestBody SaveMatchingDto saveMatchingDto) throws Exception {
		boolean result = matchingService.updateStartDate(saveMatchingDto);
		return new ResponseEntity<>(new CustomResponseDto<Boolean>(1, "과외 시작일 등록됨", result), HttpStatus.OK);
	}
	
	@PutMapping("/updateenddate")
	public ResponseEntity<?> updateEndDate(@Valid @RequestBody SaveMatchingDto saveMatchingDto) throws Exception {
		boolean result = matchingService.updateEndDate(saveMatchingDto);
		return new ResponseEntity<>(new CustomResponseDto<Boolean>(1, "과외 종료일 등록됨", result), HttpStatus.OK);
	}
	
	@DeleteMapping("/deletematching")
	public ResponseEntity<?> deleteMatchingByStudentTeacherName(@Valid @RequestBody SaveMatchingDto saveMatchingDto) throws Exception {
		boolean result = matchingService.deleteMatchingByStudentTeacherName(saveMatchingDto);
		return new ResponseEntity<>(new CustomResponseDto<Boolean>(1, "과외 성사 삭제됨", result), HttpStatus.OK);
	}
}
