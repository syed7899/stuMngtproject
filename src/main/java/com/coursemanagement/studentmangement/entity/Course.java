package com.coursemanagement.studentmangement.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "course")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "title")
	private String title;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "instructor_id")
	// @JsonDeserialize
	// JsonSerialize
	// @JsonBackReference
	// @JsonIgnore
	// @JsonIgnoreProperties
	// @JsonSerialize
	// @JsonBackReference
	// @JsonManagedReference
	//@JsonIgnoreProperties(ignoreUnknown = true, value = {"students"})
	@JsonIgnore
	private Instructor instructor;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "course_id")
	private List<Review> reviews;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	@JsonIgnore
	private List<Student> students;

	@Column(name = "price")
	@Positive(message = "Price cannot be Negative")
	@DecimalMin(value = "500", inclusive = false,message = "Courses Price Cannot be Less then 500")
	@DecimalMax(value = "3000", inclusive = false,message = "Courses Price Cannot be greater then 3000")
	private BigDecimal price;

	public Course() {

	}
	/*
	 * public Course(String title) { this.title = title; }
	 */

	public int getId() {
		return id;
	}

	public Course(String title, BigDecimal price) {
		this.title = title;
		this.price = price;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Course(int id, String title) {
		this.id = id;
		this.title = title;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void add(Review review) {
		if (reviews == null) {
			reviews = new ArrayList();
		}

		reviews.add(review);
	}

	// add a convenience method to add a course
	public void addStudent(Student student) {
		if (students == null) {
			students = new ArrayList();
		}

		students.add(student);
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}

}
