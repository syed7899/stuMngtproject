package com.coursemanagement.studentmangement.rest;

import java.util.List;
import java.util.Optional;

import com.coursemanagement.studentmangement.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coursemanagement.studentmangement.entity.Course;
import com.coursemanagement.studentmangement.entity.Review;
import com.coursemanagement.studentmangement.respository.CourseRepository;
import com.coursemanagement.studentmangement.respository.ReviewRepository;

@RestController
@RequestMapping("/review")
public class ReviewController {


	@Autowired
	private ReviewServiceImpl reviewServiceImpl;
	
	
	@GetMapping("/reviews")
	public List<Review> getAllReviews(){
		return reviewServiceImpl.getAllReviews();
	}
	

	@GetMapping("/reviews/{reviewId}")
	public Review getReviewById(@PathVariable int reviewId){
		return reviewServiceImpl.getReviewById(reviewId);
	}
	
	
	@PostMapping(value="/reviews",consumes = {"application/json"})
	public Review saveReview(@RequestBody Review review) {
    	return reviewServiceImpl.saveReview(review);
	}
	
	
	@PutMapping("/reviews")
	public Review updateReview(@RequestBody Review review) {
		return reviewServiceImpl.saveReview(review);
	}
	
	@DeleteMapping("/reviews/{reviewId}")
	public void deleteReview(@PathVariable int reviewId) {
		reviewServiceImpl.deleteReview(reviewId);
	}
	
	@GetMapping("/reviews/{reviewId}/course/{courseId}")
	public void addReviewToCourse(@PathVariable int reviewId,@PathVariable int courseId) {
		reviewServiceImpl.addReviewToCourse(reviewId,courseId);
	}
	

}
