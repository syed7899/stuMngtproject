package com.coursemanagement.studentmangement.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student {

	public Student() {

	}

	public Student(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	@Size(min=2,message = "FirstName Should have atleast 2 characters ")
	@NotNull(message = "FirstName cannot be null")
	private String firstName;

	@Column(name = "last_name")
	@Size(min=2,message = "LastName Should have atleast 2 characters")
	private String lastName;

	@Column(name = "email")
	@Email(message = "Email should be valid")
	private String email;


	@ManyToMany(fetch=FetchType.LAZY,cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name="course_student",
			joinColumns=@JoinColumn(name="student_id"),
			inverseJoinColumns = @JoinColumn(name="course_id")
			)
	//@JsonIgnore
	//@JsonBackReference
	//@JsonIgnoreProperties(ignoreUnknown = true, value = {"students"})
	private List<Course> courseList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

}
