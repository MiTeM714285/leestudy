package com.springboot.leestudy.service.matching;

import java.util.List;

import com.springboot.leestudy.domain.matching.Entity.Matching;
import com.springboot.leestudy.web.dto.matching.SaveMatchingDto;

public interface MatchingService {
	
	public boolean saveMatching(SaveMatchingDto saveMatchingDto) throws Exception;
	public List<Matching> findMatchingByStudentName(String student_name) throws Exception;
	public int findMatchingCountByStudentName(String student_name) throws Exception;
	public List<Matching> findMatchingByTeacherName(String teacher_name) throws Exception;
	public Matching findMatchingByStudentTeacherName(SaveMatchingDto saveMatchingDto) throws Exception;
	public Matching findMatchingByMatchingCode(int matching_code) throws Exception;
	public int findMatchingCountByTeacherName(String teacher_name) throws Exception;
	public boolean checkMatchingIsExist(SaveMatchingDto saveMatchingDto) throws Exception;
	public boolean updateStudentIsReady(SaveMatchingDto saveMatchingDto) throws Exception;
	public boolean updateTeacherIsReady(SaveMatchingDto saveMatchingDto) throws Exception;
	public boolean updateStartDate(SaveMatchingDto saveMatchingDto) throws Exception;
	public boolean updateEndDate(SaveMatchingDto saveMatchingDto) throws Exception;
	public boolean deleteMatchingByStudentTeacherName(SaveMatchingDto saveMatchingDto) throws Exception;
}
