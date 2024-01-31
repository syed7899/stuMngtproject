package com.coursemanagement.studentmangement.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.coursemanagement.studentmangement.entity.Course;
import com.coursemanagement.studentmangement.entity.InstructorDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstructorResponse {
	
	private int id;

	private String firstName;

	private String lastName;

	private String email;

	private Date createdAt;


	private InstructorDetail instructorDetail;

	private List<Course> courseList;


}
