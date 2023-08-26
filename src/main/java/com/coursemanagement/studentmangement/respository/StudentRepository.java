package com.coursemanagement.studentmangement.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coursemanagement.studentmangement.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
