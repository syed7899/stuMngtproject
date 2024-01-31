package com.coursemanagement.studentmangement.rest;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.coursemanagement.studentmangement.model.InstructorResponse;
import com.coursemanagement.studentmangement.service.InstructorServiceImpl;

import com.coursemanagement.studentmangement.utility.EmailValidator;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coursemanagement.studentmangement.entity.Course;
import com.coursemanagement.studentmangement.entity.Instructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/instructor")
@Log4j2
public class InstructorController {

	@Autowired
	private InstructorServiceImpl instructorService;


	@GetMapping("/instructors")
	public ResponseEntity<List<Instructor>> getAllInstructors() {
		log.info("getAllInstructors has been Called -- within controller");
		List<Instructor> instructorList = instructorService.findAll();
		if (instructorList.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.of(Optional.of(instructorList));
	}

	@PostMapping(value = "instructors", consumes = { "application/json" })
	public ResponseEntity<Object> saveInstructor(@Valid @RequestBody Instructor instructor) {
	   try {
			   Instructor instructordetails = instructorService.saveInstructor(instructor);
			   log.info("instructorDetail details are saved with ID:" + instructordetails.getId());
		       URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(instructordetails.getId()).toUri();
			   return ResponseEntity.created(location).build();
		   }
	          catch(Exception e){
		       e.printStackTrace();
			   return ResponseEntity.badRequest().body("Invalid email address. Kindly check your email address!!");
			}
	}

	// @GetMapping("/new/instructors/{instructorId}")
	@GetMapping("/instructors/{instructorId}")
	public ResponseEntity<InstructorResponse> getInstructor(@PathVariable int instructorId) {
		log.info("Find Instructor with id {}:" + instructorId);
		InstructorResponse instructorResponse = instructorService.findById(instructorId);
		return new ResponseEntity<>(instructorResponse,HttpStatus.OK);
	}

	@PutMapping("/instructors")
	public ResponseEntity<Instructor> updateInstructor(@Valid @RequestBody Instructor instructor) {
		try {
			Instructor updatedInstructor = instructorService.updateInstructor(instructor);
			log.info("updated Instructor with below details:{} " + updatedInstructor);
			return ResponseEntity.ok().body(updatedInstructor);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/instructors/{instructorId}")
	public ResponseEntity<?> deleteInstructor(@PathVariable int instructorId) {
		try {
			instructorService.deleteById(instructorId);
			log.info("Deleted Instructor with Id:" + instructorId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/instructor/{instructorId}/courses")
	public ResponseEntity<List<Course>> getCoursesForInstructor(@PathVariable int instructorId) {
		log.info("Courses For the  Instructor with Instructor id is {}" + instructorId);
		List<Course> courseList = instructorService.getCoursesOfInstructor(instructorId);
		log.info("CourseList in controller:" + courseList);
		if (courseList != null) {
			return ResponseEntity.of(Optional.of(courseList));
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
/*
	@GetMapping("/instructors/dob")
	public List<Instructor> getAllInstructorsBetweenDates(@RequestParam String startDate,
			@RequestParam String endDate) {
		log.info("getAllInstructorsBetweenDates has been called with startDate {} and endDate {}", startDate, endDate);
		return instructorService.getInstructorBetweenDates(startDate, endDate);
	}
*/
	/*
	 * @PostMapping(value="/instructors",consumes = {"application/json"}) public
	 * ResponseEntity<Instructor> saveInstructor(@RequestBody Instructor instructor)
	 * { Instructor instructorDetail= instructorService.save(instructor);
	 * log.info("instructorDetail details are :"+instructorDetail); // return
	 * instructorDetail; URI location=
	 * ServletUriComponentsBuilder.fromCurrentRequest() .path("/{id}")
	 * .buildAndExpand(instructorDetail.getId()) .toUri();
	 * log.info("location Details " +location); return
	 * ResponseEntity.created(location).build(); }
	 */
}
