package com.coursemanagement.studentmangement.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coursemanagement.studentmangement.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
