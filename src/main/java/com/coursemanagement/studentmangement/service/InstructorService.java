package com.coursemanagement.studentmangement.service;

import com.coursemanagement.studentmangement.entity.Course;
import com.coursemanagement.studentmangement.entity.Instructor;
import com.coursemanagement.studentmangement.model.CourseResponse;
import com.coursemanagement.studentmangement.model.InstructorResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface InstructorService {
	
	public List<Instructor> findAll();
	public Instructor findById(int instructorId);
	public Instructor save(Instructor instructor);
	public Instructor saveInstructor(Instructor instructor);
	public void deleteById(int instructorId);

	public Instructor updateInstructor(Instructor instructor);

	public List<Course> getCoursesOfInstructor(int instructorId);

	public  List<Instructor> getInstructorBetweenDates(String startDate, String endDate);

	/*
	public void addCourseToInstructor(int courseId, int instructorId);
	public List<Course> getCoursesByPrice(BigDecimal startPrice,BigDecimal endPrice);
  */
}
