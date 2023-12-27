package com.coursemanagement.studentmangement.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.coursemanagement.studentmangement.entity.InstructorDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstructorResponse {
	
	private int id;

	private String firstName;

	private String lastName;
	
	private InstructorDetail instructorDetail;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
    public static class InstructorDetail {
		
		
		private int id;
		private String youtube;
		private String hobby;

	}

}
