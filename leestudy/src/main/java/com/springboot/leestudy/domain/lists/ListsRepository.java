package com.springboot.leestudy.domain.lists;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.leestudy.domain.lists.Entity.ListUniversity;

@Mapper
public interface ListsRepository {
	
	public List<ListUniversity> getUniversityListAll();
	public List<String> getSubjectCategoryListAll();
	public List<String> getSubjectNameListBySubjectCategory(String subject_category);
	public List<String> getAddressPart1ListAll();
	public List<String> getAddressPart2ListByAddressPart1(String address_part1);
	public List<String> getPersonalityNameListAll();
	
}
