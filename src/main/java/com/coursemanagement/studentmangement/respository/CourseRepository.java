package com.coursemanagement.studentmangement.respository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coursemanagement.studentmangement.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	public List<Course> findByPriceBetween(BigDecimal startprice,BigDecimal endprice);

}
