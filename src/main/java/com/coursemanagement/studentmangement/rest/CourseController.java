package com.coursemanagement.studentmangement.rest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coursemanagement.studentmangement.entity.Course;
import com.coursemanagement.studentmangement.entity.Instructor;
import com.coursemanagement.studentmangement.service.CourseServiceImpl;

@RestController
@RequestMapping("/course")
public class CourseController {


	@Autowired
	private CourseServiceImpl courseServiceImpl;
	
	
	@GetMapping("/courses")
	public List<Course> getAllCourses(){
		return courseServiceImpl.findAll();		
	}
	
	
	@GetMapping("/courses/{courseId}")
	public Course getAllCourses(@PathVariable int courseId){
		Course theCourse= courseServiceImpl.findById(courseId);
		return theCourse;
	}
	
	
	
	@PostMapping(value="/courses",consumes = {"application/json"})
	public Course saveCourses(@RequestBody Course course){
		Course theCourse= courseServiceImpl.save(course);
		return theCourse;
	}
	
	
	@DeleteMapping("/courses/{courseId}")
	public void deleteCourse(@PathVariable int courseId){
		courseServiceImpl.deleteById(courseId);
		
	}
	
	
	@GetMapping("/courses/{courseId}/instructor/{instructorId}")
	public void addCourseToInstructor(@PathVariable int courseId, @PathVariable int instructorId){
		courseServiceImpl.addCourseToInstructor(courseId, instructorId);
	}
	
	
	
	@GetMapping("/courses/Byprice")
	public List<Course> getCoursesByPrice(@RequestParam BigDecimal startprice,
			                              @RequestParam BigDecimal endprice){
		return courseServiceImpl.getCoursesByPrice(startprice, endprice);
	}
	
	
	
	
	

}
