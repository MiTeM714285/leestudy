package com.springboot.leestudy.service.matching;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.leestudy.domain.matching.MatchingRepository;
import com.springboot.leestudy.domain.matching.Entity.Matching;
import com.springboot.leestudy.domain.user.UserRepository;
import com.springboot.leestudy.domain.user.Entity.UserCommon;
import com.springboot.leestudy.web.dto.matching.SaveMatchingDto;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService {
	
	private final MatchingRepository matchingRepository;
	private final UserRepository userRepository;

	@Override
	public boolean saveMatching(SaveMatchingDto saveMatchingDto) throws Exception {
		Matching matching = saveMatchingDto.toEntity();
		return matchingRepository.saveMatching(matching) >= 1 ? true : false;
	}
	
	@Override
	public boolean checkMatchingIsExist(SaveMatchingDto saveMatchingDto) throws Exception {
		Matching matching = saveMatchingDto.toEntity(); 
		return matchingRepository.checkMatchingIsExist(matching) >= 1 ? true : false;
	}

	@Override
	public List<Matching> findMatchingByStudentName(String student_name) throws Exception {
		List<Matching> matchingList = matchingRepository.findMatchingByStudentName(student_name);
		List<Matching> matchingListWithNamePicture = new ArrayList<Matching>();
		for (Matching element : matchingList) {
			String teacher_name = element.getTeacher_name();
			UserCommon userCommon = userRepository.findUserCommonByUsername(teacher_name);
			element.setNickname(userCommon.getNickname());
			element.setPicture(userCommon.getPicture());
			matchingListWithNamePicture.add(element);
		}
		return matchingListWithNamePicture;
	}

	@Override
	public int findMatchingCountByStudentName(String student_name) throws Exception {
		return matchingRepository.findMatchingCountByStudentName(student_name);
	}

	@Override
	public List<Matching> findMatchingByTeacherName(String teacher_name) throws Exception {
		List<Matching> matchingList = matchingRepository.findMatchingByTeacherName(teacher_name);
		List<Matching> matchingListWithNamePicture = new ArrayList<Matching>();
		for (Matching element : matchingList) {
			String student_name = element.getStudent_name();
			UserCommon userCommon = userRepository.findUserCommonByUsername(student_name);
			element.setNickname(userCommon.getNickname());
			element.setPicture(userCommon.getPicture());
			matchingListWithNamePicture.add(element);
		}
		return matchingListWithNamePicture;
	}

	@Override
	public int findMatchingCountByTeacherName(String teacher_name) throws Exception {
		return matchingRepository.findMatchingCountByTeacherName(teacher_name);
	}
	
	@Override
	public Matching findMatchingByStudentTeacherName(SaveMatchingDto saveMatchingDto) throws Exception {
		Matching matching = saveMatchingDto.toEntity();
		return matchingRepository.findMatchingByStudentTeacherName(matching);
	}

	@Override
	public boolean updateStudentIsReady(SaveMatchingDto saveMatchingDto) throws Exception {
		Matching matching = saveMatchingDto.toEntity();
		return matchingRepository.updateStudentIsReady(matching) >= 1 ? true : false;
	}

	@Override
	public boolean updateTeacherIsReady(SaveMatchingDto saveMatchingDto) throws Exception {
		Matching matching = saveMatchingDto.toEntity();
		return matchingRepository.updateTeacherIsReady(matching) >= 1 ? true : false;
	}

	@Override
	public boolean updateStartDate(SaveMatchingDto saveMatchingDto) throws Exception {
		Matching matching = saveMatchingDto.toEntity();
		return matchingRepository.updateStartDate(matching) >= 1 ? true : false;
	}

	@Override
	public boolean deleteMatchingByStudentTeacherName(SaveMatchingDto saveMatchingDto) throws Exception {
		Matching matching = saveMatchingDto.toEntity();
		return matchingRepository.deleteMatchingByStudentTeacherName(matching) >= 1 ? true : false;
	}

	@Override
	public boolean updateEndDate(SaveMatchingDto saveMatchingDto) throws Exception {
		Matching matching = saveMatchingDto.toEntity();
		return matchingRepository.updateEndDate(matching) >= 1 ? true : false;
	}

	@Override
	public Matching findMatchingByMatchingCode(int matching_code) throws Exception {
		return matchingRepository.findMatchingByMatchingCode(matching_code);
	}


}
