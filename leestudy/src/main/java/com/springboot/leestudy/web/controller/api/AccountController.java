package com.springboot.leestudy.web.controller.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.leestudy.service.account.AccountService;
import com.springboot.leestudy.web.dto.CustomResponseDto;
import com.springboot.leestudy.web.dto.account.AccountDeleteReqDto;
import com.springboot.leestudy.web.dto.account.PasswordCheckReqDto;
import com.springboot.leestudy.web.dto.account.UpdateUserCommonReqDto;
import com.springboot.leestudy.web.dto.account.UpdateUserStudentReqDto;
import com.springboot.leestudy.web.dto.account.UpdateUserTeacherReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
	
	private final AccountService accountService;
	
	@GetMapping("/count")
	public ResponseEntity<?> countUserCommonByRole(String role) throws Exception {
		int count = accountService.countUserCommonByRole(role);
		return new ResponseEntity<>(new CustomResponseDto<Integer>(1, "회원 수 조회완료", count), HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/checkpassword")
	public ResponseEntity<?> checkPassword(@Valid @RequestBody PasswordCheckReqDto passwordCheckReqDto, BindingResult bindingResult) throws Exception {
		boolean result = accountService.checkPassword(passwordCheckReqDto);
		if(result) {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(1, "비밀번호 일치", result), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(-1, "비밀번호 불일치", result), HttpStatus.BAD_REQUEST);
		}
	}
	

	@PutMapping("/modify-common")
	public ResponseEntity<?> updateUserCommonByUsername(@Valid @RequestBody UpdateUserCommonReqDto updateUserCommonReqDto, BindingResult bindingResult) throws Exception {
		boolean result = accountService.updateUserCommonByUsername(updateUserCommonReqDto);
		if(result) {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(1, "회원정보(공통) 수정완료", result), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(-1, "회원정보(공통) 수정실패", result), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/modify-student")
	public ResponseEntity<?> updateUserStudentByUsername(@Valid @RequestBody UpdateUserStudentReqDto updateUserStudentReqDto, BindingResult bindingResult) throws Exception {
		boolean result = accountService.updateUserStudentByUsername(updateUserStudentReqDto);
		if(result) {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(1, "회원정보(학생) 수정완료", result), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(-1, "회원정보(학생) 수정실패", result), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/modify-teacher")
	public ResponseEntity<?> updateUserTeacherByUsername(@Valid @RequestBody UpdateUserTeacherReqDto updateUserTeacherReqDto, BindingResult bindingResult) throws Exception {
		boolean result = accountService.updateUserTeacherByUsername(updateUserTeacherReqDto);
		if(result) {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(1, "회원정보(선생) 수정완료", result), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(-1, "회원정보(선생) 수정실패", result), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteUserCommonByUsername(@Valid @RequestBody AccountDeleteReqDto accountDeleteReqDto, BindingResult bindingResult) throws Exception {
		boolean result = accountService.deleteUserCommonByUsername(accountDeleteReqDto.getUsername());
		if(result) {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(1, "회원탈퇴 완료", result), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(-1, "회원탈퇴 실패", result), HttpStatus.BAD_REQUEST);
		}
	}


}
