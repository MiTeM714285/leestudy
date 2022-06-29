package com.springboot.leestudy.web.controller.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.leestudy.domain.lists.Entity.ListUniversity;
import com.springboot.leestudy.service.lists.ListsService;
import com.springboot.leestudy.web.dto.CustomResponseDto;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lists")
public class ListsController {
	
	private final ListsService listsService;
	
	@GetMapping("/university") // 대학교 리스트 전체 들고오기
	public ResponseEntity<?> getUniversityListAll() throws Exception {
		List<ListUniversity> universityList = listsService.getUniversityListAll();
		return new ResponseEntity<>(new CustomResponseDto<List<ListUniversity>>(1, "대학교 목록 로드" , universityList), HttpStatus.OK);
	}
	
	@GetMapping("/subjectcategory") // 과목 카테고리 리스트 전체 들고오기
	public ResponseEntity<?> getSubjectCategoryListAll() throws Exception {
		List<String> subjectCategoryList = listsService.getSubjectCategoryListAll();
		return new ResponseEntity<>(new CustomResponseDto<List<String>>(1, "과목 카테고리 로드" , subjectCategoryList), HttpStatus.OK);
	}
	
	@GetMapping("/addresspart1") // 주소(시) 리스트 전체 들고오기
	public ResponseEntity<?> getAddressPart1ListAll() throws Exception {
		List<String> addressPart1List = listsService.getAddressPart1ListAll();
		return new ResponseEntity<>(new CustomResponseDto<List<String>>(1, "주소(시) 로드" , addressPart1List), HttpStatus.OK);
	}
	
	@GetMapping("/addresspart2/{address_part1}") // 주소(구) 리스트 전체 들고오기
	public ResponseEntity<?> getAddressPart2ListByAddressPart1(@PathVariable String address_part1) throws Exception {
		List<String> addressPart2List = listsService.getAddressPart2ListByAddressPart1(address_part1);
		return new ResponseEntity<>(new CustomResponseDto<List<String>>(1, "주소(구) 로드" , addressPart2List), HttpStatus.OK);
	}
	
	@GetMapping("/personality") // 성격 리스트 전체 들고오기
	public ResponseEntity<?> getPersonalityNameListAll() throws Exception {
		List<String> personalityNameList = listsService.getPersonalityNameListAll();
		return new ResponseEntity<>(new CustomResponseDto<List<String>>(1, "성격 목록 로드" , personalityNameList), HttpStatus.OK);
	}
}