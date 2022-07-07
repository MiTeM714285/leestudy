package com.springboot.leestudy.domain.review;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.leestudy.domain.review.Entity.Review;

@Mapper
public interface ReviewRepository {
	public int saveReview(Review review) throws Exception;
	public int findReviewCountByMatchingCode(int matching_code) throws Exception;
	public List<Review> findReviewAll(Review review) throws Exception;
	public Review findReviewByMatchingCode(int matching_code) throws Exception;
}
