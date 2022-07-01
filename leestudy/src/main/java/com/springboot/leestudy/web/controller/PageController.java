package com.springboot.leestudy.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.leestudy.config.auth.PrincipalDetails;
import com.springboot.leestudy.domain.lists.Entity.ListUniversity;
import com.springboot.leestudy.service.account.AccountService;
import com.springboot.leestudy.service.detail.DetailService;
import com.springboot.leestudy.service.lists.ListsService;
import com.springboot.leestudy.web.dto.detail.FindStudentInfoByDetailRespDto;
import com.springboot.leestudy.web.dto.detail.FindTeacherInfoByDetailRespDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageController {
	
	private final ListsService listsService;
	private final AccountService accountService;
	private final DetailService detailService;
	
	@GetMapping("/login") // 로그인 화면
	public String login() throws Exception {
		return "login";
	}
	
	@GetMapping("/join-student") // 학생 가입화면
	public String joinStudent(Model model) throws Exception {
		
		// 주소(시), 과목 카테고리, 과목 이름 리스트를 불러와서 Model에 속성 저장
		
		List<String> subjectCategoryList = listsService.getSubjectCategoryListAll();
		List<List<String>> subjectNameList = new ArrayList<List<String>>();
		List<String> addressPart1List = listsService.getAddressPart1ListAll();
		int count_student = accountService.countUserCommonByRole("USER_STUDENT");
		int count_teacher = accountService.countUserCommonByRole("USER_TEACHER");
		
		for(String category : subjectCategoryList) {
			List<String> subjectNamesByCategory = listsService.getSubjectNameListBySubjectCategory(category);
			subjectNameList.add(subjectNamesByCategory);
		}
		
		model.addAttribute("subjectCategoryList",subjectCategoryList);
		model.addAttribute("subjectNameList",subjectNameList);
		model.addAttribute("addressPart1List",addressPart1List);
		model.addAttribute("count_student",count_student);
		model.addAttribute("count_teacher",count_teacher);
		
		return "join-student";
	}
	
	@GetMapping("/join-teacher") // 선생님 가입화면
	public String joinTeacher(Model model) throws Exception {
		
		// 성격, 주소(시), 과목 카테고리, 과목 이름, 대학명 리스트를 불러와서 Model에 속성 저장
		
		List<String> personalityNameList = listsService.getPersonalityNameListAll();
		List<String> subjectCategoryList = listsService.getSubjectCategoryListAll();
		List<List<String>> subjectNameList = new ArrayList<List<String>>();
		List<ListUniversity> universityList = listsService.getUniversityListAll();
		List<String> addressPart1List = listsService.getAddressPart1ListAll();
		int count_student = accountService.countUserCommonByRole("USER_STUDENT");
		int count_teacher = accountService.countUserCommonByRole("USER_TEACHER");
		
		for(String category : subjectCategoryList) {
			List<String> subjectNamesByCategory = listsService.getSubjectNameListBySubjectCategory(category);
			subjectNameList.add(subjectNamesByCategory);
		}
		
		model.addAttribute("personalityNameList",personalityNameList);
		model.addAttribute("subjectCategoryList",subjectCategoryList);
		model.addAttribute("subjectNameList",subjectNameList);
		model.addAttribute("universityList",universityList);
		model.addAttribute("addressPart1List",addressPart1List);
		model.addAttribute("count_student",count_student);
		model.addAttribute("count_teacher",count_teacher);
		return "join-teacher";
	}
	
	@GetMapping("/auth/search")
	public String search(@AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception { // 로그인 후 학생 및 선생 판별
		System.out.println(principalDetails.getRole());
		if (principalDetails.getRole().equals("USER_STUDENT")) { // 학생이면
			return  "redirect:/auth/search/teacher";
		} else { // 선생이면
			return "redirect:/auth/search/student";
		}
	}
	
	@GetMapping("/auth/search/student")
	public String searchStudent(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) throws Exception {
		int count_student = accountService.countUserStudentIsUrgent();
		List<String> subjectCategoryList = listsService.getSubjectCategoryListAll();
		List<String> addressPart1List = listsService.getAddressPart1ListAll();
		
		model.addAttribute("role",principalDetails.getRole());
		model.addAttribute("count_student",count_student);
		model.addAttribute("subjectCategoryList",subjectCategoryList);
		model.addAttribute("addressPart1List",addressPart1List);
		return "auth/search/search-student";
	}
	
	@GetMapping("/auth/search/teacher")
	public String searchTeacher(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) throws Exception {
		int count_teacher = accountService.countUserCommonByRole("USER_TEACHER");
		List<String> subjectCategoryList = listsService.getSubjectCategoryListAll();
		List<String> addressPart1List = listsService.getAddressPart1ListAll();
		
		model.addAttribute("role",principalDetails.getRole());
		model.addAttribute("count_teacher",count_teacher);
		model.addAttribute("subjectCategoryList",subjectCategoryList);
		model.addAttribute("addressPart1List",addressPart1List);
		return "auth/search/search-teacher";
	}
	
	@GetMapping("/auth/detail/student")
	public String detailStudent(@Valid String username, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) throws Exception {
		FindStudentInfoByDetailRespDto findStudentInfoByDetailRespDto = detailService.findStudentInfoByDetail(username);
		model.addAttribute("role",principalDetails.getRole());
		model.addAttribute("studentinfo",findStudentInfoByDetailRespDto);
		
		return "auth/detail/detail-student";
	}
	
	@GetMapping("/auth/detail/teacher")
	public String detailTeacher(@Valid String username, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) throws Exception {
		FindTeacherInfoByDetailRespDto findTeacherInfoByDetailRespDto = detailService.findTeacherInfoByDetail(username);
		model.addAttribute("role",principalDetails.getRole());
		model.addAttribute("teacherinfo",findTeacherInfoByDetailRespDto);
		
		return "auth/detail/detail-teacher";
	}
	
	@GetMapping("/auth/modify")
	public String modify(@AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception { // 로그인 후 학생 및 선생 판별
		if (principalDetails.getRole().equals("USER_STUDENT")) { // 학생이면
			return  "redirect:/auth/modify/student";
		} else { // 선생이면
			return "redirect:/auth/modify/teacher";
		}
	}
	
	@GetMapping("/auth/modify/student")
	public String modifyStudent(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) throws Exception {
		
		// 주소(시), 과목 카테고리, 과목 이름 리스트를 불러와서 Model에 속성 저장
		
		List<String> subjectCategoryList = listsService.getSubjectCategoryListAll();
		List<List<String>> subjectNameList = new ArrayList<List<String>>();
		List<String> addressPart1List = listsService.getAddressPart1ListAll();
		int count_student = accountService.countUserCommonByRole("USER_STUDENT");
		int count_teacher = accountService.countUserCommonByRole("USER_TEACHER");
		
		for(String category : subjectCategoryList) {
			List<String> subjectNamesByCategory = listsService.getSubjectNameListBySubjectCategory(category);
			subjectNameList.add(subjectNamesByCategory);
		}
		
		model.addAttribute("role",principalDetails.getRole());
		model.addAttribute("subjectCategoryList",subjectCategoryList);
		model.addAttribute("subjectNameList",subjectNameList);
		model.addAttribute("addressPart1List",addressPart1List);
		model.addAttribute("count_student",count_student);
		model.addAttribute("count_teacher",count_teacher);
		return "auth/modify/modify-student";
	}
	
	@GetMapping("/auth/modify/teacher")
	public String modifyTeacher(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) throws Exception {
		// 성격, 주소(시), 과목 카테고리, 과목 이름, 대학명 리스트를 불러와서 Model에 속성 저장
		
		List<String> personalityNameList = listsService.getPersonalityNameListAll();
		List<String> subjectCategoryList = listsService.getSubjectCategoryListAll();
		List<List<String>> subjectNameList = new ArrayList<List<String>>();
		List<ListUniversity> universityList = listsService.getUniversityListAll();
		List<String> addressPart1List = listsService.getAddressPart1ListAll();
		int count_student = accountService.countUserCommonByRole("USER_STUDENT");
		int count_teacher = accountService.countUserCommonByRole("USER_TEACHER");
		
		for(String category : subjectCategoryList) {
			List<String> subjectNamesByCategory = listsService.getSubjectNameListBySubjectCategory(category);
			subjectNameList.add(subjectNamesByCategory);
		}
		
		model.addAttribute("personalityNameList",personalityNameList);
		model.addAttribute("subjectCategoryList",subjectCategoryList);
		model.addAttribute("subjectNameList",subjectNameList);
		model.addAttribute("universityList",universityList);
		model.addAttribute("addressPart1List",addressPart1List);
		model.addAttribute("count_student",count_student);
		model.addAttribute("count_teacher",count_teacher);
		model.addAttribute("role",principalDetails.getRole());
		return "auth/modify/modify-teacher";
	}
}
