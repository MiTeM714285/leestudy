package com.springboot.leestudy.service.review;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.leestudy.domain.review.ReviewRepository;
import com.springboot.leestudy.domain.review.Entity.Review;
import com.springboot.leestudy.domain.user.UserRepository;
import com.springboot.leestudy.web.dto.review.FindReviewAllReqDto;
import com.springboot.leestudy.web.dto.review.FindStudentInfoByWriteReviewRespDto;
import com.springboot.leestudy.web.dto.review.FindTeacherInfoByWriteReviewRespDto;
import com.springboot.leestudy.web.dto.review.SaveReviewReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
	
	private final UserRepository userRepository;
	private final ReviewRepository reviewRepository;

	@Override
	public FindStudentInfoByWriteReviewRespDto findStudentInfoByWriteReview(String username) throws Exception {
		return userRepository.findStudentInfoWriteReview(username).toWriteReviewRespDto();
	}

	@Override
	public FindTeacherInfoByWriteReviewRespDto findTeacherInfoByWriteReview(String username) throws Exception {
		return userRepository.findTeacherInfoWriteReview(username).toWriteReviewRespDto();
	}

	@Override
	public boolean saveReview(SaveReviewReqDto saveReviewReqDto) throws Exception {
		Review review = saveReviewReqDto.toEntity();
		return reviewRepository.saveReview(review) >= 1 ? true : false;
	}

	@Override
	public boolean findReviewIsExistByMatchingCode(int matching_code) throws Exception {
		return reviewRepository.findReviewCountByMatchingCode(matching_code) >= 1 ? true : false;
	}

	@Override
	public List<Review> findReviewAll(FindReviewAllReqDto findReviewAllReqDto) throws Exception {
		Review review = findReviewAllReqDto.toEntity();
		return reviewRepository.findReviewAll(review);
	}

	@Override
	public Review findReviewByMatchingCode(int matching_code) throws Exception {
		return reviewRepository.findReviewByMatchingCode(matching_code);
	}

	@Override
	public int countUserCommonByUsername(String username) throws Exception {
		return userRepository.countUserCommonByUsername(username);
	}

	@Override
	public double findReviewScorePercent() throws Exception {
		int reviewCount = reviewRepository.findReviewCountAll(); // 우선 리뷰 수 구하기
		double percent = 0; // 리뷰 수가 없을때 0 반환.
		if (reviewCount >= 1) { // 리뷰 수가 하나 이상이라면
			percent = reviewRepository.findReviewScorePercent();
		}
		
		return percent;
	}
}
