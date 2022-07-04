package com.springboot.leestudy.service.detail;

import com.springboot.leestudy.web.dto.detail.FindStudentInfoByDetailRespDto;
import com.springboot.leestudy.web.dto.detail.FindTeacherInfoByDetailRespDto;

public interface DetailService {
	public FindTeacherInfoByDetailRespDto findTeacherInfoByDetail(String username) throws Exception;
	public FindStudentInfoByDetailRespDto findStudentInfoByDetail(String username) throws Exception;
}
