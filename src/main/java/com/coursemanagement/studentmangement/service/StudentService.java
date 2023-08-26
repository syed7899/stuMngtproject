package com.coursemanagement.studentmangement.service;

import com.coursemanagement.studentmangement.entity.Course;
import com.coursemanagement.studentmangement.entity.Student;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface StudentService {

    public List<Student> getAllStudents();

    public Student getAllStudents(int studentId);

    public Student saveStudent(Student student);

    public Student updateStudent(Student student);

    public void deleteStudent(int studentId);

    public void addCoursesForStudent(int studentId,int courseId);

    public List<Course> getCoursesForStudent(int studentId);




}
