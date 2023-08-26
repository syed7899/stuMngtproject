package com.coursemanagement.studentmangement.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
		Optional<Course> theCourse = courseRepository.findById(courseId);
		Course course = theCourse.get();
		Optional<Instructor> instructor = instructorRepository.findById(instructorId);
		Instructor theInstructor = instructor.get();
		theInstructor.addCourse(course);
		instructorRepository.save(theInstructor);

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
