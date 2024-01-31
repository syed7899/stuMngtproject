package com.coursemanagement.studentmangement.service;

import java.math.BigDecimal;
import java.util.List;

import com.coursemanagement.studentmangement.exception.CourseNotFoundException;
import com.coursemanagement.studentmangement.exception.InstructorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coursemanagement.studentmangement.entity.Course;
import com.coursemanagement.studentmangement.entity.Instructor;
import com.coursemanagement.studentmangement.respository.CourseRepository;
import com.coursemanagement.studentmangement.respository.InstructorRepository;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private InstructorRepository instructorRepository;

	@Override
	public List<Course> findAll() {

		return courseRepository.findAll();
	}

	@Override
	public Course findById(int courseId) {
		return courseRepository.findById(courseId).get();
	}

	@Override
	public Course save(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public void addCourseToInstructor(int courseId, int instructorId) {
		System.out.println("courseId ::" + courseId + " ---- " + "Instructor id::" + instructorId);
		Course theCourse = courseRepository.findById(courseId)
				.orElseThrow(() -> new CourseNotFoundException("Course Not found with given id:****** "+ courseId,"COURSE_NOT_FOUND"));
		Instructor instructor = instructorRepository.findById(instructorId)
				.orElseThrow(() -> new InstructorNotFoundException("Instructor Not found with given id***** "+instructorId,"Product_NOT_FOUND"));
		instructor.addCourse(theCourse);
		instructorRepository.save(instructor);
	}

	@Override
	public void deleteById(int courseId) {
		courseRepository.deleteById(courseId);
	}

	@Override
	public List<Course> getCoursesByPrice(BigDecimal startprice, BigDecimal endprice) {
		List<Course> courseList=courseRepository.findByPriceBetween(startprice, endprice);
		return courseList;
	}

}
