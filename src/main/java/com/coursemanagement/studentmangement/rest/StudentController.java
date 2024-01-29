package com.coursemanagement.studentmangement.rest;

import java.util.List;

import com.coursemanagement.studentmangement.service.StudentServiceImpl;
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
import com.coursemanagement.studentmangement.entity.Student;
import com.coursemanagement.studentmangement.respository.CourseRepository;
import com.coursemanagement.studentmangement.respository.StudentRepository;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentServiceImpl studentServiceImpl;

	@GetMapping("/students")
	public List<Student> getAllStudents() {
		return studentServiceImpl.getAllStudents();
	}
	

	@GetMapping("/students/{studentId}")
	public Student getAllStudents(@PathVariable int studentId){
		return studentServiceImpl.getAllStudents(studentId);
	}
	
	@PostMapping("/students")
	public Student saveStudent(@RequestBody Student student) {
		return studentServiceImpl.saveStudent(student);
	}

	@PutMapping("/students")
	public Student updateStudent(@RequestBody Student student) {
		return studentServiceImpl.updateStudent(student);
	}
	
	
	@DeleteMapping("/students/{studentId}")
	public void deleteStudent(@PathVariable int studentId ) {
		studentServiceImpl.deleteStudent(studentId);
	}
	
	
	//post
	@GetMapping("course/{courseId}/student/{studentId}")
	public void addCoursesForStudent(@PathVariable int studentId,@PathVariable int courseId){
		studentServiceImpl.addCoursesForStudent(studentId,courseId);
	}
	
	
	@GetMapping("course/student/{studentId}")
	public List<Course> getCoursesForStudent(@PathVariable int studentId){
		return studentServiceImpl.getCoursesForStudent(studentId);
	}
}
