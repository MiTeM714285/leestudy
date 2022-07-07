package com.springboot.leestudy.service.review;

import java.util.List;

import com.springboot.leestudy.domain.review.Entity.Review;
import com.springboot.leestudy.web.dto.review.FindReviewAllReqDto;
import com.springboot.leestudy.web.dto.review.FindStudentInfoByWriteReviewRespDto;
import com.springboot.leestudy.web.dto.review.FindTeacherInfoByWriteReviewRespDto;
import com.springboot.leestudy.web.dto.review.SaveReviewReqDto;

public interface ReviewService {
	
	public FindStudentInfoByWriteReviewRespDto findStudentInfoByWriteReview(String username) throws Exception ;
	public FindTeacherInfoByWriteReviewRespDto findTeacherInfoByWriteReview(String username) throws Exception ;
	public boolean saveReview(SaveReviewReqDto saveReviewReqDto) throws Exception;
	public boolean findReviewIsExistByMatchingCode(int matching_code) throws Exception;
	public List<Review> findReviewAll(FindReviewAllReqDto findReviewAllReqDto) throws Exception;
	public Review findReviewByMatchingCode(int matching_code) throws Exception;
	public int countUserCommonByUsername(String username);
}
