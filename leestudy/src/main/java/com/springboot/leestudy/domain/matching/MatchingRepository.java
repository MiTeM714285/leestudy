package com.springboot.leestudy.domain.matching;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.leestudy.domain.matching.Entity.Matching;

@Mapper
public interface MatchingRepository {
	
	public int saveMatching(Matching matching) throws Exception;
	public List<Matching> findMatchingByStudentName(String student_name) throws Exception;
	public int findMatchingCountByStudentName(String student_name) throws Exception;
	public List<Matching> findMatchingByTeacherName(String teacher_name) throws Exception;
	public Matching findMatchingByStudentTeacherName(Matching matching) throws Exception;
	public Matching findMatchingByMatchingCode(int matching_code) throws Exception;
	public int findMatchingCountByTeacherName(String teacher_name) throws Exception;
	public int checkMatchingIsExist(Matching matching) throws Exception;
	public int updateStudentIsReady(Matching matching) throws Exception;
	public int updateTeacherIsReady(Matching matching) throws Exception;
	public int updateStartDate(Matching matching) throws Exception;
	public int updateEndDate(Matching matching) throws Exception;
	public int deleteMatchingByStudentTeacherName(Matching matching) throws Exception;
}
