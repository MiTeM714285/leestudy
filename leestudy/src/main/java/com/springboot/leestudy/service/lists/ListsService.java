package com.springboot.leestudy.service.lists;

import java.util.List;

import com.springboot.leestudy.domain.lists.Entity.ListUniversity;

public interface ListsService {
	
	public List<ListUniversity> getUniversityListAll() throws Exception;
	public List<String> getSubjectCategoryListAll() throws Exception;
	public List<String> getSubjectNameListBySubjectCategory(String subject_category) throws Exception;
	public List<String> getAddressPart1ListAll() throws Exception;
	public List<String> getAddressPart2ListByAddressPart1(String address_part1) throws Exception;
	public List<String> getPersonalityNameListAll() throws Exception;
	
}
