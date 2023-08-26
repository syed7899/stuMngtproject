package com.coursemanagement.studentmangement.service;

import com.coursemanagement.studentmangement.entity.Review;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ReviewService {

    public List<Review> getAllReviews();

    public Review getReviewById(int reviewId);

    public Review saveReview(Review review);

    public Review updateReview(Review review);

    public void deleteReview(int reviewId);

    public void addReviewToCourse(int reviewId,int courseId);

}
