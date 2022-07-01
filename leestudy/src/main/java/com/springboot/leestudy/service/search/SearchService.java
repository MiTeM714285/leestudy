package com.springboot.leestudy.service.search;

import java.util.List;

import com.springboot.leestudy.web.dto.search.FindStudentInfoBySearchReqDto;
import com.springboot.leestudy.web.dto.search.FindTeacherInfoBySearchRespDto;
import com.springboot.leestudy.web.dto.search.FindTeacherInfoBySearchReqDto;
import com.springboot.leestudy.web.dto.search.FindStudentInfoBySearchRespDto;

public interface SearchService {
	public List<FindTeacherInfoBySearchRespDto> findTeacherInfoBySearch(FindTeacherInfoBySearchReqDto findTeacherInfoBySearchReqDto) throws Exception;
	public List<FindStudentInfoBySearchRespDto> findStudentInfoBySearch(FindStudentInfoBySearchReqDto findStudentInfoBySearchReqDto) throws Exception;
}
