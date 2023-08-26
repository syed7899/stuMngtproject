package com.coursemanagement.studentmangement.service;

import java.math.BigDecimal;
import java.util.List;

import com.coursemanagement.studentmangement.entity.Course;

public interface CourseService {
	
	public List<Course> findAll();
	public Course findById(int courseId);
	public Course save(Course course);
	public void deleteById(int courseId);
	public void addCourseToInstructor(int courseId, int instructorId);
	public List<Course> getCoursesByPrice(BigDecimal startPrice,BigDecimal endPrice);

}
