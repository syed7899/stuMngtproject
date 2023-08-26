package com.coursemanagement.studentmangement.rest;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.coursemanagement.studentmangement.service.InstructorServiceImpl;
import com.coursemanagement.studentmangement.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class InstructorController {


	private static final Logger logger= LoggerFactory.getLogger(InstructorController.class);

	@Autowired
	private InstructorServiceImpl instructorService;

	@GetMapping("/instructors")
	public List<Instructor> getAllInstructors() {
		logger.info("getAllInstructors has been Called -- within controller");
		List<Instructor> listOfInstructor= instructorService.findAll();
		// listOfInstructor.forEach(i->System.out.println(i));
		 return listOfInstructor;
		//return ResponseEntity.ok().body()
	}

	@PostMapping(value="/instructors",consumes = {"application/json"})
	public ResponseEntity<Instructor> saveInstructor(@RequestBody Instructor instructor) {
		Instructor instructorDetail= instructorService.save(instructor);
		logger.info("instructorDetail details are :"+instructorDetail);
		// return instructorDetail;
		URI location= ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(instructorDetail.getId())
				.toUri();
		logger.info("location Details " +location);
		return ResponseEntity.created(location).build();
	}


	@GetMapping("/instructors/{instructorId}")
	public Instructor getInstructor(@PathVariable int instructorId) {
		logger.info("Instructor with id {}:"+instructorId);
		Instructor instructor= instructorService.findById(instructorId);
		if (instructor == null) {
			logger.error("Within Exception Block");
			throw new UserNotFoundException("Instructor id could not be found -"+instructorId);
		}
		return instructor;
	}



	@PutMapping("/instructors")
	public Instructor updateInstructor(@Valid @RequestBody Instructor instructor) {
		Instructor updatedInstructor= instructorService.updateInstructor(instructor);
		logger.info("updated Instructor with below details:{} "+updatedInstructor);
		return updatedInstructor;
	}

	@DeleteMapping("/instructors/{instructorId}")
	public void deleteInstructor(@PathVariable int instructorId) {
		instructorService.deleteById(instructorId);
		logger.info("Deleted Instructor with Id: {}"+instructorId);

	}
	
	@GetMapping("/instructor/{instructorId}/courses")
	public ResponseEntity<List<Course>> getCoursesForInstructor(@PathVariable int instructorId){
		logger.info("Courses For the  Instructor with Instructor id is {}"+ instructorId);
		Optional<Instructor> instructor=instructorService.getCoursesOfInstructor(instructorId);
		if (instructor.isPresent()) {
			Instructor theInstructor = instructor.get();
			List<Course> courseList = theInstructor.getCourseList();
			return ResponseEntity.ok(courseList);
		} else {
			return ResponseEntity.notFound().build();
		}
	}


	@GetMapping("/instructors/dob")
	public List<Instructor> getAllInstructorsBetweenDates(@RequestParam String startDate,
														  @RequestParam String endDate) {
		logger.info("getAllInstructorsBetweenDates has been called with startDate {} and enDate {}",startDate,endDate);
		return instructorService.getInstructorBetweenDates(startDate,endDate);
	}

}
