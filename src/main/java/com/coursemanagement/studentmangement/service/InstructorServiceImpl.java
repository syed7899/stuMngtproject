package com.coursemanagement.studentmangement.service;

import com.coursemanagement.studentmangement.entity.Course;
import com.coursemanagement.studentmangement.entity.Instructor;
import com.coursemanagement.studentmangement.respository.CourseRepository;
import com.coursemanagement.studentmangement.respository.InstructorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService {


	private static final Logger logger= LoggerFactory.getLogger(InstructorServiceImpl.class);

	@Autowired
	private InstructorRepository instructorRepository;

	@Override
	public List<Instructor> findAll() {
		List<Instructor>  listInstructor=instructorRepository.findAll();
        listInstructor.forEach(i->System.out.print(i));
		return listInstructor;
	}

	@Override
	public Instructor findById(int instructorId) {
		Optional<Instructor> instructor = instructorRepository.findById(instructorId);
		Instructor theInstructor = instructor.orElse(null);
		return theInstructor;
	}

	@Override
	public Instructor save(Instructor instructor) {
		Instructor returnedInstructor= instructorRepository.save(instructor);
		return returnedInstructor;
	}

	@Override
	public void deleteById(int instructorId) {
		/*
		Optional<Instructor> optionalInstructor = instructorRepository.findById(instructorId);
		Instructor instructor=optionalInstructor.orElse(null);
		if (instructor == null) {
			throw new UserNotFoundException("Instructor Not Found with ID:-" + instructorId);
		}
		Instructor theInstructor = instructor;
		instructorRepository.delete(theInstructor);
		*/
		 instructorRepository.deleteById(instructorId);
	}

	@Override
	public Instructor updateInstructor(Instructor instructor) {
		return instructorRepository.save(instructor);
	}





	public Optional<Instructor> getCoursesOfInstructor(@PathVariable int instructorId) {
		return instructorRepository.findById(instructorId);
	}



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
		System.out.println("startDate :::"+date1 +" "+"endDate :::"+date2);
		return instructorRepository.findByCreatedAtBetween(date1, date2);
	}
}
