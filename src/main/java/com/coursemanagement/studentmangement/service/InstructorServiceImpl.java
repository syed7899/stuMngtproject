package com.coursemanagement.studentmangement.service;

import com.coursemanagement.studentmangement.entity.Course;
import com.coursemanagement.studentmangement.entity.Instructor;
import com.coursemanagement.studentmangement.exception.InstructorNotFoundException;
import com.coursemanagement.studentmangement.model.InstructorResponse;
import com.coursemanagement.studentmangement.respository.InstructorRepository;

import com.coursemanagement.studentmangement.utility.EmailValidator;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class InstructorServiceImpl implements InstructorService {



	@Autowired
	private InstructorRepository instructorRepository;

	@Override
	public List<Instructor> findAll() {
		List<Instructor>  listInstructor=instructorRepository.findAll();
		return listInstructor;
	}

	@Override	
	public InstructorResponse findById(int instructorId) {
		Instructor instructor = 
				instructorRepository.findById(instructorId)
				.orElseThrow(() -> new InstructorNotFoundException("Instructor Not found with given id:"+instructorId,"PRODUCT_NOT_FOUND"));

		InstructorResponse instructorResponse= new InstructorResponse();
		BeanUtils.copyProperties(instructor,instructorResponse);
		return instructorResponse;
	}

	@Override
	public Instructor save(Instructor instructor) {
		Instructor returnedInstructor= instructorRepository.save(instructor);
		return returnedInstructor;
	}

	@Override
	public void deleteById(int instructorId) {
		Instructor instructor = 
				instructorRepository.findById(instructorId)
				.orElseThrow(() -> new InstructorNotFoundException("Instructor Not found with given id:"+instructorId,"PRODUCT_NOT_FOUND"));
		 instructorRepository.delete(instructor);
	}

	@Override
	public Instructor updateInstructor(Instructor instructor) {
		return instructorRepository.save(instructor);
	}





	public List<Course> getCoursesOfInstructor(@PathVariable int instructorId) {
		log.info("Courses for Id :"+instructorId);
		Instructor instructor= instructorRepository.findById(instructorId)
				.orElseThrow(() -> new InstructorNotFoundException("Instructor Not found with given id:"+instructorId,"PRODUCT_NOT_FOUND"));
        List<Course> courseList=instructor.getCourseList();
		log.info("courseList Is:============"+courseList);
		return courseList;
	}

/*

	public List<Instructor> getInstructorBetweenDates(String startDate, String endDate){
		Date date1=null;
		Date date2=null;
		System.out.println("startDate ::"+startDate +" "+"endDate ::"+endDate);
		try {
			date1 = new SimpleDateFormat("yyyy-mm-dd").parse(startDate);
			date2 = new SimpleDateFormat("yyyy-mm-dd").parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String startDate = simpleDateFormat.format(1979-01-07);
		String endDate = simpleDateFormat.format(1990-01-07);
		*/
	/*
		System.out.println("startDate :::"+date1 +" "+"endDate :::"+date2);
		return instructorRepository.findByCreatedAtBetween(date1, date2);
	}
*/
	@Override
	public Instructor saveInstructor(Instructor instructor) {
		Instructor returninstructor=null;
		String regexPattern = "^(?=.*[@])(?:(?:[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*)@(?:gmail\\.com|yahoo\\.com|yahoo\\.co\\.in|gmail\\.co\\.in))$";
		String emailaddress=instructor.getEmail();
		Boolean emailValid= EmailValidator.patternMatches(emailaddress,regexPattern);
		log.info(emailValid);
		log.info(emailaddress);
		if (emailValid) {
			instructor.setEmail(emailaddress);
			 returninstructor = instructorRepository.save(instructor);
		}else{
			throw new RuntimeException("Invalid email..plz check your emailId");
		}
          return returninstructor;
	}
}
