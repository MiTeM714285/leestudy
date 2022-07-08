package com.springboot.leestudy.web.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.leestudy.domain.review.Entity.Review;
import com.springboot.leestudy.service.review.ReviewService;
import com.springboot.leestudy.web.dto.CustomResponseDto;
import com.springboot.leestudy.web.dto.review.FindReviewAllReqDto;
import com.springboot.leestudy.web.dto.review.SaveReviewReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {
	
	private final ReviewService reviewService;
	
	@GetMapping("/reviewpercent")
	public ResponseEntity<?> findReviewScorePercent() throws Exception { // 고객만족도(리뷰평점 백분율)
		int reviewCount = reviewService.findReviewCountAll(); // 우선 리뷰 수 구하기
		double percent = 0; // 리뷰 수가 없을때 0 반환.
		if (reviewCount >= 1) { // 리뷰 수가 하나 이상이라면
			percent = reviewService.findReviewScorePercent();
		}
		return new ResponseEntity<>(new CustomResponseDto<Double>(1, "리뷰평점 백분율 조회완료", percent), HttpStatus.OK);
	}

	@PostMapping("/savereview")
	public ResponseEntity<?> saveReview(@Valid @RequestBody SaveReviewReqDto saveReviewReqDto, BindingResult bindingResult) throws Exception {
		boolean result = reviewService.saveReview(saveReviewReqDto);
		
		if(result) {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(1, "리뷰 등록 완료", result), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CustomResponseDto<Boolean>(-1, "리뷰 등록 실패", result), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/checkreview")
	public ResponseEntity<?> findReviewIsExistByMatchingCode(@Valid int matching_code) throws Exception {
		boolean result = reviewService.findReviewIsExistByMatchingCode(matching_code);
		return new ResponseEntity<>(new CustomResponseDto<Boolean>(1, "matching_code로 리뷰 코드 존재유무 확인", result), HttpStatus.OK);
	}
	
	@GetMapping("/checkteacherisexist")
	public ResponseEntity<?> countUserCommonByUsername(@Valid String username) throws Exception {
		int result = reviewService.countUserCommonByUsername(username);
		return new ResponseEntity<>(new CustomResponseDto<Integer>(1, "선생 존재유무 조회", result), HttpStatus.OK);
	}
	
	@PostMapping("/reviewlist")
	public ResponseEntity<?> findReviewAll(@Valid @RequestBody FindReviewAllReqDto findReviewAllReqDto, BindingResult bindingResult) throws Exception {
		List<Review> reviewList = reviewService.findReviewAll(findReviewAllReqDto);
		return new ResponseEntity<>(new CustomResponseDto<List<Review>>(1, "리뷰 리스트 조회", reviewList), HttpStatus.OK);
	}
}
