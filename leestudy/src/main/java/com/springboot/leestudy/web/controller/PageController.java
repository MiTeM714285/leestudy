package com.springboot.leestudy.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.leestudy.config.auth.PrincipalDetails;
import com.springboot.leestudy.domain.lists.Entity.ListUniversity;
import com.springboot.leestudy.service.lists.ListsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageController {
	
	private final ListsService listsService;
	
	@GetMapping("/auth/login") // 로그인 화면
	public String login() throws Exception {
		return "auth/login";
	}
	
	@GetMapping("/auth/join-student") // 학생 가입화면
	public String joinStudent(Model model) throws Exception {
		
		// 주소(시), 과목 카테고리, 과목 이름 리스트를 불러와서 Model에 속성 저장
		
		List<String> subjectCategoryList = listsService.getSubjectCategoryListAll();
		List<List<String>> subjectNameList = new ArrayList<List<String>>();
		List<String> addressPart1List = listsService.getAddressPart1ListAll();
		
		for(String category : subjectCategoryList) {
			List<String> subjectNamesByCategory = listsService.getSubjectNameListBySubjectCategory(category);
			subjectNameList.add(subjectNamesByCategory);
		}
		
		model.addAttribute("subjectCategoryList",subjectCategoryList);
		model.addAttribute("subjectNameList",subjectNameList);
		model.addAttribute("addressPart1List",addressPart1List);
		
		return "auth/join-student";
	}
	
	@GetMapping("/auth/join-teacher") // 선생님 가입화면
	public String joinTeacher(Model model) throws Exception {
		
		// 성격, 주소(시), 과목 카테고리, 과목 이름, 대학명 리스트를 불러와서 Model에 속성 저장
		
		List<String> personalityNameList = listsService.getPersonalityNameListAll();
		List<String> subjectCategoryList = listsService.getSubjectCategoryListAll();
		List<List<String>> subjectNameList = new ArrayList<List<String>>();
		List<ListUniversity> universityList = listsService.getUniversityListAll();
		List<String> addressPart1List = listsService.getAddressPart1ListAll();
		
		for(String category : subjectCategoryList) {
			List<String> subjectNamesByCategory = listsService.getSubjectNameListBySubjectCategory(category);
			subjectNameList.add(subjectNamesByCategory);
		}
		
		model.addAttribute("personalityNameList",personalityNameList);
		model.addAttribute("subjectCategoryList",subjectCategoryList);
		model.addAttribute("subjectNameList",subjectNameList);
		model.addAttribute("universityList",universityList);
		model.addAttribute("addressPart1List",addressPart1List);
		return "auth/join-teacher";
	}
	

	
	@GetMapping("/search")
	public String search(@AuthenticationPrincipal PrincipalDetails principalDetails) { // 로그인 후 학생 및 선생 판별
		System.out.println(principalDetails.getRole());
		if (principalDetails.getRole().equals("USER_STUDENT")) { // 학생이면
			return  "redirect:/search/teacher";
		} else { // 선생이면
			return "redirect:/search/student";
		}
	}
	
	@GetMapping("/search/student")
	public String searchStudent() {
		return "search/search-student";
	}
	
	@GetMapping("/search/teacher")
	public String searchTeacher() {
		return "search/search-teacher";
	}
}
