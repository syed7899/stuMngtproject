package com.coursemanagement.studentmangement.respository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coursemanagement.studentmangement.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer >{
	
	
	
	List<Instructor> findByCreatedAtBetween(Date startDate, Date endDate);

}
