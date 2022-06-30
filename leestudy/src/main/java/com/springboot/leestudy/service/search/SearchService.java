package com.springboot.leestudy.service.search;

import java.util.List;

import com.springboot.leestudy.web.dto.search.FindTeacherInfoBySearchReqDto;
import com.springboot.leestudy.web.dto.search.FindTeacherInfoBySearchRespDto;

public interface SearchService {
	public List<FindTeacherInfoBySearchRespDto> findTeacherInfoBySearch(FindTeacherInfoBySearchReqDto findTeacherInfoBySearchReqDto) throws Exception;
}
