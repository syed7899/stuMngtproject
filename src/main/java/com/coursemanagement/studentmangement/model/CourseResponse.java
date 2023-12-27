package com.coursemanagement.studentmangement.model;

import java.util.List;

import com.coursemanagement.studentmangement.entity.Course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponse {
	
	private List<Course> courseList;

}
