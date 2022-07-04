package com.springboot.leestudy.service.account;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.leestudy.config.auth.PrincipalDetails;
import com.springboot.leestudy.domain.user.UserRepository;
import com.springboot.leestudy.domain.user.Entity.UserCommon;
import com.springboot.leestudy.web.dto.account.PasswordCheckReqDto;
import com.springboot.leestudy.web.dto.account.UpdateUserCommonReqDto;
import com.springboot.leestudy.web.dto.account.UpdateUserStudentReqDto;
import com.springboot.leestudy.web.dto.account.UpdateUserTeacherReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	@Value("${file.path}") // application.yml에서의 설정을 따름
	private String filePath; // @Value 어노테이션을 따라, filePath의 값을 지정
	private final UserRepository userRepository;
	
	@Override
	public boolean checkPassword(PasswordCheckReqDto passwordCheckReqDto) throws Exception {
		String currentPassword = userRepository.findPasswordByUsername(passwordCheckReqDto.getUsername());
		
		boolean result = new BCryptPasswordEncoder().matches(passwordCheckReqDto.getPassword(), currentPassword);
		System.out.println(result);
		return result;
	}

	@Override
	public boolean updateUserCommonByUsername(UpdateUserCommonReqDto updateUserCommonReqDto) throws Exception {
		if(updateUserCommonReqDto.getPassword().equals("") || updateUserCommonReqDto.getPassword() == null) { // 비밀번호 제외하고 업데이트시
			return userRepository.updateUserCommonByUsernameWithoutPassword(updateUserCommonReqDto.toEntity()) > 0;
		} else { // 비밀번호 포함 업데이트시
			return userRepository.updateUserCommonByUsername(updateUserCommonReqDto.toEntity()) > 0;
		}
	}

	@Override
	public boolean updateUserStudentByUsername(UpdateUserStudentReqDto updateUserStudentReqDto) throws Exception {
		if(updateUserStudentReqDto.getStudent_phonenum().equals("") || updateUserStudentReqDto.getStudent_phonenum() == null) { // 전화번호 제외하고 업데이트시
			return userRepository.updateUserStudentByUsernameWithoutPhonenum(updateUserStudentReqDto.toEntity()) > 0;
		} else {
			return userRepository.updateUserStudentByUsername(updateUserStudentReqDto.toEntity()) > 0;
		}
	}

	@Override
	public boolean updateUserTeacherByUsername(UpdateUserTeacherReqDto updateUserTeacherReqDto) throws Exception {
		if(updateUserTeacherReqDto.getTeacher_phonenum().equals("") || updateUserTeacherReqDto.getTeacher_phonenum() == null) { // 전화번호 제외하고 업데이트시
			return userRepository.updateUserTeacherByUsernameWithoutPhonenum(updateUserTeacherReqDto.toEntity()) > 0;
		} else {
			return userRepository.updateUserTeacherByUsername(updateUserTeacherReqDto.toEntity()) > 0;
		}
	}

	@Override
	public boolean deleteUserCommonByUsername(String username) throws Exception {
		return userRepository.deleteUserCommonByUsername(username) > 0;
	}

	@Override
	public int countUserCommonByRole(String role) throws Exception {
		return userRepository.countUserCommonByRole(role);
	}

	@Override
	public int countUserStudentIsUrgent() throws Exception {
		// TODO Auto-generated method stub
		return userRepository.countUserStudentIsUrgent();
	}

	@Override
	public boolean updateProfileImg(MultipartFile file, PrincipalDetails principalDetails) throws Exception {
		if(file != null) {
			
			String originalFileName = file.getOriginalFilename();
			String tempFileName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFileName;
			Path uploadPath = Paths.get(filePath, "custom/" + tempFileName); // filePath까지의 경로 다음의 custom 디렉토리에 tempFileName
			
			System.out.println("tempFileName : " + tempFileName);
			System.out.println("uploadPath : " + uploadPath);
			
			File f = new File(filePath + "custom");
			if (!f.exists()) { // 해당 경로가 존재하지 않는다면
				f.mkdirs(); // 경로 생성
			}
			
			try {  // 파일 쓰기
				Files.write(uploadPath, file.getBytes());
				UserCommon userCommon = principalDetails.getUserCommon();
				userCommon.setPicture(tempFileName);
				return userRepository.updateProfileImg(userCommon) > 0 ? true : false;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
