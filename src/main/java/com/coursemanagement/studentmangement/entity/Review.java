package com.coursemanagement.studentmangement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="review")
public class Review {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	@JsonIgnore
	private int id;
	
	@Column(name="comment")
	private String comments;
	
	
	public Review() {
	
	}




	public Review(String comments) {
		this.comments = comments;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getComments() {
		return comments;
	}




	public void setComments(String comments) {
		this.comments = comments;
	}




	@Override
	public String toString() {
		return "Review [id=" + id + ", comments=" + comments + "]";
	}
	
	
	

}
