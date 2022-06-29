package com.springboot.leestudy.web.controller.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.leestudy.config.auth.PrincipalDetails;
import com.springboot.leestudy.domain.user.Entity.UserCommon;
import com.springboot.leestudy.domain.user.Entity.UserStudent;
import com.springboot.leestudy.domain.user.Entity.UserTeacher;
import com.springboot.leestudy.service.CoolSMS.CoolSMSService;
import com.springboot.leestudy.service.auth.AuthService;
import com.springboot.leestudy.web.dto.CustomResponseDto;
import com.springboot.leestudy.web.dto.auth.SaveUserCommonDto;
import com.springboot.leestudy.web.dto.auth.SaveUserStudentDto;
import com.springboot.leestudy.web.dto.auth.SaveUserTeacherDto;
import com.springboot.leestudy.web.dto.auth.UsernameCheckReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	private final CoolSMSService coolSMSService;
	
	@GetMapping("/info-usercommon") // 유저(공통) 정보 조회
	public ResponseEntity<?> getUserCommon(@AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {
		System.out.println(principalDetails.getUserCommon().getUsername());
		UserCommon userCommon = authService.findUserCommonByUsername((principalDetails.getUserCommon().getUsername()));
		PrincipalDetails newPrincipalDetails = principalDetails; // 회원정보수정 등 할때 세션갱신용
		newPrincipalDetails.getUserCommon().setPassword(userCommon.getPassword()); // 세션정보 갱신
		newPrincipalDetails.getUserCommon().setNickname(userCommon.getNickname()); // 세션정보 갱신
		newPrincipalDetails.getUserCommon().setPicture(userCommon.getPicture()); // 세션정보 갱신
		newPrincipalDetails.getUserCommon().setGender(userCommon.getGender()); // 세션정보 갱신
		newPrincipalDetails.getUserCommon().setAge(userCommon.getAge()); // 세션정보 갱신
		newPrincipalDetails.getUserCommon().setAddress_part1(userCommon.getAddress_part1()); // 세션정보 갱신
		newPrincipalDetails.getUserCommon().setAddress_part2(userCommon.getAddress_part2()); // 세션정보 갱신
		return new ResponseEntity<>(new CustomResponseDto<PrincipalDetails>(1, "세션정보 - UserCommon",newPrincipalDetails), HttpStatus.OK);
	}
	@GetMapping("/info-userdetail") // 유저(학생 및 선생) 정보 조회
	public ResponseEntity<?> getUserStudent(@AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {
		String role = principalDetails.getRole();
		if(role.equals("USER_STUDENT")) {
			UserStudent userStudent = authService.findUserStudentByUsername(principalDetails.getUsername());
			return new ResponseEntity<>(new CustomResponseDto<UserStudent>(1, "세션정보 - UserStudent", userStudent), HttpStatus.OK);
		} else {
			UserTeacher userTeacher = authService.findUserTeacherByUsername(principalDetails.getUsername());
			return new ResponseEntity<>(new CustomResponseDto<UserTeacher>(1, "세션정보 - UserTeacher", userTeacher), HttpStatus.OK);
		}
	}
	
	@GetMapping("/join-common/sendSMS")
	public String sendSMS(String phoneNumber) throws Exception {
		String authenticationCode = null;
		authenticationCode = coolSMSService.sendAuthenticationCode(phoneNumber);
		return authenticationCode;
	}
	
	@GetMapping("/join-common/username")
	public ResponseEntity<?> checkUsername(@Valid UsernameCheckReqDto usernameCheckReqDto, BindingResult bindingResult) throws Exception {
		boolean result = authService.checkUsername(usernameCheckReqDto.getUsername());
		
		if(result) {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(1, "해당 아이디 사용가능", result), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(-1, "중복된 아이디입니다.", result), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/join-common/{role}")
	public ResponseEntity<?> saveUserCommon(@PathVariable String role, @Valid @RequestBody SaveUserCommonDto saveUserCommonDto, BindingResult bindingResult) throws Exception {
		boolean result = authService.saveUserCommon(role, saveUserCommonDto);
		
		if(result) {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(1, "회원가입(공통) 완료", result), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(-1, "회원가입(공통) 실패", result), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/join-student")
	public ResponseEntity<?> saveUserStudent(@Valid @RequestBody SaveUserStudentDto saveUserStudentDto, BindingResult bindingResult) throws Exception {
		boolean result = authService.saveUserStudent(saveUserStudentDto);
		
		if(result) {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(1, "회원가입(학생) 완료", result), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(-1, "회원가입(학생) 실패", result), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/join-teacher")
	public ResponseEntity<?> saveUserTeacher(@Valid @RequestBody SaveUserTeacherDto saveUserTeacherDto, BindingResult bindingResult) throws Exception {
		boolean result = authService.saveUserTeacher(saveUserTeacherDto);
		
		if(result) {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(1, "회원가입(선생) 완료", result), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(-1, "회원가입(선생) 실패", result), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/loginfail") // 로그인 실패시 이쪽으로
	public String loginFail() throws Exception {
		return "<script>"
		         + "alert(\"로그인에 실패하였습니다.\");"
		         + "location.href=\"/auth/login\";"
		         + "</script>";
	}
}
