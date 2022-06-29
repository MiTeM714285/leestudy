package com.springboot.leestudy.web.controller.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/join-common/sendSMS")
	public String sendSMS(String phoneNumber) {
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
}
