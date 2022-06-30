package com.springboot.leestudy.service.lists;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.leestudy.domain.lists.ListsRepository;
import com.springboot.leestudy.domain.lists.Entity.ListUniversity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListsServiceImpl implements ListsService {
	
	private final ListsRepository listsRepository;

	@Override
	public List<ListUniversity> getUniversityListAll() throws Exception{
		return listsRepository.getUniversityListAll();
	}

	@Override
	public List<String> getSubjectCategoryListAll() throws Exception{
		return listsRepository.getSubjectCategoryListAll();
	}

	@Override
	public List<String> getSubjectNameListBySubjectCategory(String subject_category) throws Exception{
		return listsRepository.getSubjectNameListBySubjectCategory(subject_category);
	}

	@Override
	public List<String> getAddressPart1ListAll() throws Exception {
		return listsRepository.getAddressPart1ListAll();
	}

	@Override
	public List<String> getAddressPart2ListByAddressPart1(String address_part1) throws Exception {
		return listsRepository.getAddressPart2ListByAddressPart1(address_part1);
	}
	
	@Override
	public List<String> getPersonalityNameListAll() throws Exception{
		return listsRepository.getPersonalityNameListAll();
	}


}
