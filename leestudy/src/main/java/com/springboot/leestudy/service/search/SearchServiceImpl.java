package com.springboot.leestudy.service.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.leestudy.domain.user.UserRepository;
import com.springboot.leestudy.domain.user.Entity.UserTeacherAll;
import com.springboot.leestudy.web.dto.search.FindTeacherInfoBySearchReqDto;
import com.springboot.leestudy.web.dto.search.FindTeacherInfoBySearchRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
	
	private final UserRepository userRepository;

	@Override
	public List<FindTeacherInfoBySearchRespDto> findTeacherInfoBySearch(FindTeacherInfoBySearchReqDto findTeacherInfoBySearchReqDto) throws Exception {
		System.out.println(findTeacherInfoBySearchReqDto.toString());
		UserTeacherAll userTeacherAll = findTeacherInfoBySearchReqDto.toEntity();
		List<FindTeacherInfoBySearchRespDto> FindTeacherInfoBySearchRespDtos = new ArrayList<FindTeacherInfoBySearchRespDto>();
		List<UserTeacherAll> userTeacherAlls = userRepository.findTeacherInfoBySearch(userTeacherAll);
		for (UserTeacherAll element : userTeacherAlls) {
			FindTeacherInfoBySearchRespDtos.add(element.toRespDto());
		}
		return FindTeacherInfoBySearchRespDtos;
	}

}
